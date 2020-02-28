package com.zgy.handle.userService.service.structure;

import com.zgy.handle.userService.model.structure.Industry;
import com.zgy.handle.userService.model.structure.IndustryDTO;
import com.zgy.handle.userService.repository.structure.IndustryRepository;
import com.zgy.handle.userService.service.SystemRefactorService;
import com.zgy.handle.userService.util.tree.TreeConvert;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class IndustryService extends SystemRefactorService<Industry,IndustryDTO> {
    private IndustryRepository industryRepository;
    @Autowired
    public IndustryService(IndustryRepository industryRepository) {
        super(industryRepository);
        this.industryRepository = industryRepository;
    }

    /**
     * 根据id列表获取所有的对象信息
     * @param idList
     * @return
     */
    public Set<Industry> findAllByIdIn(List<Long> idList){
        return industryRepository.findByIdIn(idList);
    }

    /**
     * 动态查询
     * @param industryDTO
     * @param pageable
     * @return
     */
    @Override
    public Page<Industry> findByDynamicQuery(Pageable pageable, IndustryDTO industryDTO){
        Specification<Industry> specification = Specification
                .where(StringUtils.isBlank(industryDTO.getName())?null:industryRepository.blurStrQuery("name",industryDTO.getName()))
                .and(StringUtils.isBlank(industryDTO.getCode())?null:industryRepository.blurStrQuery("code",industryDTO.getCode()))
                .and(StringUtils.isBlank(industryDTO.getShortName())?null:industryRepository.blurStrQuery("shortName",industryDTO.getShortName()))
                ;
        return industryRepository.findAll(specification,pageable);
    }

    public List<IndustryDTO> getIndustryDtoList(List<IndustryDTO> industryDTOList){
        TreeConvert treeUtils = new TreeConvert(industryDTOList);
        try {
            List<IndustryDTO> industryDTOS = treeUtils.toJsonArray(IndustryDTO.class);
            return industryDTOS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<IndustryDTO> getIndustryDtoList(){
        // 获取所有的上级机构
        List<Industry> parentEnterpriseList = industryRepository.findByParentIdIsNull();
        // 遍历顶级企业下的所有子企业
        List<IndustryDTO> industryDTOList = getDepartmentDtoList(parentEnterpriseList);
        // log.info("获取到的最终数据为: " + industryDTOList.toString());
        return industryDTOList;
    }

    private List<IndustryDTO> getDepartmentDtoList(List<Industry> industryList){
        List<IndustryDTO> industryDTOList = new ArrayList<>();
        for (Industry industry : industryList){
            IndustryDTO industryDTO = new IndustryDTO();
            industryDTO.setCode(industry.getCode());
            industryDTO.setName(industry.getName());
            industryDTO.setNote(industry.getNote());
            industryDTO.setId(industry.getId().toString());
            industryDTO.setParentId(industry.getParent()==null?"":industry.getParent().getId().toString());
            industryDTO.setChildren(getChildrenEnterprise(industry.getId()));
            industryDTOList.add(industryDTO);
        }
        return industryDTOList;
    }

    /**
     * 获取某一企业下的所有子企业列表
     * @param parentId
     * @return
     */
    private List<IndustryDTO> getChildrenEnterprise(Long parentId){
        List<Industry> chidrenEnterprise = industryRepository.findByParentId(parentId);
        return getDepartmentDtoList(chidrenEnterprise);
    }

    @Override
    public void beforeUpdate(IndustryDTO industryDTO, Industry industry) {
        industry.setNote(industryDTO.getNote());
        if (industryDTO.getParentId() != null){
            Optional<Industry> parentOptional = this.findById(Long.valueOf(industryDTO.getParentId()));
            if (parentOptional.isPresent()){
                industry.setParent(parentOptional.get());
            }
        }
    }
}
