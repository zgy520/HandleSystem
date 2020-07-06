package com.zgy.handle.userService.service.structure;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zgy.handle.userService.model.structure.Enterprise;
import com.zgy.handle.userService.model.structure.EnterpriseDTO;
import com.zgy.handle.userService.model.structure.Industry;
import com.zgy.handle.userService.repository.structure.EnterpriseRepository;
import com.zgy.handle.userService.service.SystemService;
import com.zgy.handle.userService.util.tree.TreeConvert;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EnterpriseService extends SystemService<Enterprise,EnterpriseDTO> {
    private EnterpriseRepository enterpriseRepository;
    @Autowired
    private IndustryService industryService;
    public EnterpriseService(EnterpriseRepository enterpriseRepository) {
        super(enterpriseRepository);
        this.enterpriseRepository = enterpriseRepository;
    }


    public List<EnterpriseDTO> getEnterpriseDtoList(List<EnterpriseDTO> enterpriseDTOList){
        TreeConvert treeUtils = new TreeConvert(enterpriseDTOList);
        try {
            List<EnterpriseDTO> enterpriseDTOS = treeUtils.toJsonArray(EnterpriseDTO.class);
            return enterpriseDTOS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 根据id列表获取所有的实体数据
     * @param idList
     * @return
     */
    public Set<Enterprise> findAllByIdIn(List<Long> idList){
        return enterpriseRepository.findByIdIn(idList);
    }

    /**
     * 基于分页的动态查询
     * @param enterpriseDTO
     * @param pageable
     * @return
     */
    @Override
    public Page<Enterprise> findByDynamicQuery(Pageable pageable, EnterpriseDTO enterpriseDTO){
        Specification<Enterprise> specification = Specification
                .where(StringUtils.isBlank(enterpriseDTO.getName())? null : enterpriseRepository.blurStrQuery("name",enterpriseDTO.getName()))
                .and(StringUtils.isBlank(enterpriseDTO.getCode())?null : enterpriseRepository.blurStrQuery("code",enterpriseDTO.getCode()))
                .and(StringUtils.isBlank(enterpriseDTO.getPrefix())?null : enterpriseRepository.blurStrQuery("prefix",enterpriseDTO.getPrefix()))
                .and(StringUtils.isBlank(enterpriseDTO.getCheckStatus())?null:enterpriseRepository.blurStrQuery("checkStatus",enterpriseDTO.getCheckStatus()))
                .and(StringUtils.isBlank(enterpriseDTO.getAuthorStatus())?null:enterpriseRepository.blurStrQuery("authorStatus",enterpriseDTO.getAuthorStatus()))
                .and(StringUtils.isBlank(enterpriseDTO.getShortName())?null: enterpriseRepository.blurStrQuery("shortName",enterpriseDTO.getShortName()))
                ;
        return enterpriseRepository.findAll(specification,pageable);
    }

    @Override
    public void beforeUpdate(EnterpriseDTO enterpriseDTO, Enterprise enterprise) {
        if (StringUtils.isNotBlank(enterpriseDTO.getParentId())){
            Optional<Enterprise> parentOptional = this.findById(Long.valueOf(enterpriseDTO.getParentId()));
            if (parentOptional.isPresent()){
                enterprise.setParent(parentOptional.get());
            }
        }
        if (StringUtils.isNotBlank(enterpriseDTO.getIndustryId())){
            Optional<Industry> industryOptional = industryService.findById(Long.valueOf(enterpriseDTO.getIndustryId()));
            if (industryOptional.isPresent()){
                enterprise.setBelongIndustry(industryOptional.get());
            }
        }
        enterprise.setNote(enterpriseDTO.getNote());
    }


    /**
     * 行业百分比统计
     * @return
     */
    public JSONObject industryStatic(){
        JSONObject jsonObject = new JSONObject();
        List<Enterprise> enterpriseList = enterpriseRepository.findAll()
                .stream().filter(enterprise -> enterprise.getAuthorStatus() != null && enterprise.getAuthorStatus().equals("已授权"))
                .collect(Collectors.toList());
        int count = enterpriseList.size();
        Map<String,List<Enterprise>> groupByIndustry = enterpriseList.stream()
                .collect(Collectors.groupingBy(Enterprise::getIndustry));
        for (String key : groupByIndustry.keySet()){
            int keyCount = groupByIndustry.get(key).size();
            log.info("行业:" + key + ",对应的数量为:" + keyCount + "个");
            Float bl = (float) keyCount / count * 100;
            jsonObject.put(key,String.format("%.2f", bl ) + "%");
        }
        return jsonObject;
    }

    public JSONArray getEnterpriseInfo(){
        List<Enterprise> enterpriseList = enterpriseRepository.findAll();
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        enterpriseList.stream().forEach(enterprise -> {
            JSONObject itemJson = new JSONObject();
            itemJson.put("province",enterprise.getProvince());
            itemJson.put("name",enterprise.getName());
            itemJson.put("status",enterprise.getAuthorStatus());
            itemJson.put("authorDate",StringUtils.isNotBlank(enterprise.getAuthorDate())?enterprise.getAuthorDate().substring(0,10):"");
            jsonArray.add(itemJson);
        });
        return jsonArray;
    }

    public JSONObject getEnterpriseProvinceInfo(){
        JSONObject jsonObject = new JSONObject();
        JSONArray finArray = new JSONArray();
        List<Enterprise> enterpriseList = enterpriseRepository.findAll();
        Map<String,List<Enterprise>> provinceMap = enterpriseList.stream().filter(enterprise -> StringUtils.isNotBlank(enterprise.getProvince())).collect(Collectors.groupingBy(Enterprise::getProvince));
        for (String province : provinceMap.keySet()){
            JSONObject provinceJson = new JSONObject();
            List<Enterprise> provinceEntrpriseList = provinceMap.get(province);
            provinceJson.put(province,provinceEntrpriseList.size());
            Map<String,List<Enterprise>> cityMap = provinceEntrpriseList.stream().filter(enterprise -> StringUtils.isNotBlank(enterprise.getCity())).collect(Collectors.groupingBy(Enterprise::getCity));
            JSONArray jsonArray = new JSONArray();
            for (String city : cityMap.keySet()){
                JSONObject cityJson = new JSONObject();
                //cityJson.put(city,cityMap.get(city).size());
                cityJson.put("name",city);
                cityJson.put("value",cityMap.get(city).size());
                jsonArray.add(cityJson);
            }
            provinceJson.put("city",jsonArray);
            finArray.add(provinceJson);
        }
        jsonObject.put("province",finArray);
        return jsonObject;
    }

    public void sendEmail(){

    }
}
