package com.zgy.handle.userService.service.structure;

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
import java.util.Optional;
import java.util.Set;

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
}
