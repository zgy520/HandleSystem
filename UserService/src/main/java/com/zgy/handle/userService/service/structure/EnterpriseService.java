package com.zgy.handle.userService.service.structure;

import com.zgy.handle.userService.model.structure.Enterprise;
import com.zgy.handle.userService.model.structure.EnterpriseDTO;
import com.zgy.handle.userService.model.structure.Industry;
import com.zgy.handle.userService.repository.structure.EnterpriseRepository;
import com.zgy.handle.userService.service.SystemRefactorService;
import com.zgy.handle.userService.service.SystemService;
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
public class EnterpriseService extends SystemRefactorService<Enterprise,EnterpriseDTO> {
    private EnterpriseRepository enterpriseRepository;
    @Autowired
    private IndustryService industryService;
    public EnterpriseService(EnterpriseRepository enterpriseRepository) {
        super(enterpriseRepository);
        this.enterpriseRepository = enterpriseRepository;
    }


    public List<EnterpriseDTO> getEnterpriseDtoList(){
        List<EnterpriseDTO> enterpriseDTOList = new ArrayList<>();
        // 获取所有的上级机构
        List<Enterprise> parentEnterpriseList = enterpriseRepository.findByParentIdIsNull();
        // 遍历顶级企业下的所有子企业
        enterpriseDTOList = getEnterpriseDtoList(parentEnterpriseList);
        //log.info("获取到的最终数据为: " + enterpriseDTOList.toString());
        return enterpriseDTOList;
    }

    private List<EnterpriseDTO> getEnterpriseDtoList(List<Enterprise> enterpriseList){
        List<EnterpriseDTO> enterpriseDTOList = new ArrayList<>();
        for (Enterprise parent : enterpriseList){
            EnterpriseDTO enterpriseDTO = new EnterpriseDTO();
            enterpriseDTO.setCode(parent.getCode());
            enterpriseDTO.setName(parent.getName());
            enterpriseDTO.setId(parent.getId().toString());
            enterpriseDTO.setNote(parent.getNote());
            enterpriseDTO.setShortName(parent.getShortName());
            enterpriseDTO.setParentId(parent.getParent()==null?"":parent.getParent().getId().toString());
            enterpriseDTO.setIndustryId(parent.getIndustry()==null?"":parent.getIndustry().getId().toString());
            enterpriseDTO.setChildren(getChildrenEnterprise(parent.getId()));
            enterpriseDTOList.add(enterpriseDTO);
        }
        return enterpriseDTOList;
    }

    /**
     * 获取某一企业下的所有子企业列表
     * @param parentId
     * @return
     */
    private List<EnterpriseDTO> getChildrenEnterprise(Long parentId){
        List<EnterpriseDTO> enterpriseDTOList = new ArrayList<>();
        List<Enterprise> chidrenEnterprise = enterpriseRepository.findByParentId(parentId);
        return getEnterpriseDtoList(chidrenEnterprise);
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
                enterprise.setIndustry(industryOptional.get());
            }
        }
        enterprise.setNote(enterpriseDTO.getNote());
    }
}
