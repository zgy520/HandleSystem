package com.zgy.handle.userService.service.structure;

import com.zgy.handle.userService.model.structure.Enterprise;
import com.zgy.handle.userService.model.structure.EnterpriseDTO;
import com.zgy.handle.userService.model.structure.EnterpriseRegDTO;
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
public class EnterpriseRegService extends SystemService<Enterprise, EnterpriseRegDTO> {
    private EnterpriseRepository enterpriseRepository;
    @Autowired
    private IndustryService industryService;
    public EnterpriseRegService(EnterpriseRepository enterpriseRepository) {
        super(enterpriseRepository);
        this.enterpriseRepository = enterpriseRepository;
    }


    /**
     * 基于分页的动态查询
     * @param enterpriseDTO
     * @param pageable
     * @return
     */
    @Override
    public Page<Enterprise> findByDynamicQuery(Pageable pageable, EnterpriseRegDTO enterpriseDTO){
        Specification<Enterprise> specification = Specification
                .where(StringUtils.isBlank(enterpriseDTO.getName())? null : enterpriseRepository.blurStrQuery("name",enterpriseDTO.getName()))
                ;
        return enterpriseRepository.findAll(specification,pageable);
    }

    @Override
    public void beforeUpdate(EnterpriseRegDTO enterpriseDTO, Enterprise enterprise) {
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
