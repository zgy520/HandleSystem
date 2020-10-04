package com.zgy.handle.userservice.service.structure.enterprise.query;

import com.zgy.handle.common.service.base.impl.BaseQueryServiceImpl;
import com.zgy.handle.userservice.model.dto.structure.EnterpriseQueryDTO;
import com.zgy.handle.userservice.model.structure.Enterprise;
import com.zgy.handle.userservice.model.structure.Enterprise_;
import com.zgy.handle.userservice.model.structure.Industry;
import com.zgy.handle.userservice.repository.structure.enterprise.EntperiseQueryRepository;
import com.zgy.handle.userservice.service.structure.industry.query.IndustryQueryService;
import com.zgy.handle.userservice.util.tree.TreeConvert;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EnterpriseQueryServiceImpl extends BaseQueryServiceImpl<Enterprise, EnterpriseQueryDTO> implements EnterpriseQueryService {
    @Autowired
    private IndustryQueryService industryQueryService;
    private EntperiseQueryRepository entperiseQueryRepository;

    public EnterpriseQueryServiceImpl(EntperiseQueryRepository entperiseQueryRepository) {
        super(entperiseQueryRepository);
        this.entperiseQueryRepository = entperiseQueryRepository;
    }

    @Override
    public Specification<Enterprise> querySpecification(EnterpriseQueryDTO dto) {
        Specification<Enterprise> specification = Specification
                .where(StringUtils.isBlank(dto.getName()) ? null : entperiseQueryRepository.fieldLike(Enterprise_.NAME, dto.getName()))
                .and(StringUtils.isBlank(dto.getCode()) ? null : entperiseQueryRepository.fieldLike(Enterprise_.CODE, dto.getCode()))
                .and(StringUtils.isBlank(dto.getNote()) ? null : entperiseQueryRepository.fieldLike(Enterprise_.NOTE, dto.getName()));
        return specification;
    }

    @Override
    public List<Enterprise> findBySpecification(EnterpriseQueryDTO enterpriseQueryDTO) {
        return entperiseQueryRepository.findAll(this.querySpecification(enterpriseQueryDTO));
    }

    @Override
    public List<EnterpriseQueryDTO> getTreeEnterpriseQueryDto(List<EnterpriseQueryDTO> enterpriseQueryDTOS) {
        enterpriseQueryDTOS.forEach(enterpriseQueryDTO -> {
            if (StringUtils.isNotBlank(enterpriseQueryDTO.getIndustryId())) {
                Industry industry = fetchIndustryByEnterpriseId(Long.valueOf(enterpriseQueryDTO.getIndustryId()));
                if (industry != null) {
                    enterpriseQueryDTO.setIndustryId(industry.getId().toString());
                    enterpriseQueryDTO.setIndustryName(industry.getName());
                }

            }
        });
        TreeConvert treeConvert = new TreeConvert(enterpriseQueryDTOS);
        try {
            return treeConvert.toJsonArray(EnterpriseQueryDTO.class);
        } catch (Exception ex) {

        }
        return null;
    }

    @Transactional(readOnly = true)
    public Industry fetchIndustryByEnterpriseId(Long industryId) {
        Optional<Industry> industryOptional = industryQueryService.findById(industryId);
        if (industryOptional.isPresent()) {
            return industryOptional.get();
        }
        return null;
    }
}
