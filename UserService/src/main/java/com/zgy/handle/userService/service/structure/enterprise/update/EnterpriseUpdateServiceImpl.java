package com.zgy.handle.userService.service.structure.enterprise.update;

import com.zgy.handle.userService.model.common.UniqueInfo;
import com.zgy.handle.userService.model.dto.structure.EnterpriseUpdateDTO;
import com.zgy.handle.userService.model.structure.Enterprise;
import com.zgy.handle.userService.repository.structure.enterprise.EnterpriseUpdateRepository;
import com.zgy.handle.userService.repository.structure.enterprise.EntperiseQueryRepository;
import com.zgy.handle.userService.service.base.impl.UpdateServiceImpl;
import com.zgy.handle.userService.service.structure.industry.query.IndustryQueryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnterpriseUpdateServiceImpl extends UpdateServiceImpl<Enterprise, EnterpriseUpdateDTO> implements EnterpriseUpdateService {
    @Autowired
    private IndustryQueryService industryQueryService;
    @Autowired
    private EntperiseQueryRepository entperiseQueryRepository;
    private EnterpriseUpdateRepository enterpriseUpdateRepository;

    public EnterpriseUpdateServiceImpl(EnterpriseUpdateRepository enterpriseUpdateRepository) {
        super(enterpriseUpdateRepository);
        this.enterpriseUpdateRepository = enterpriseUpdateRepository;
    }

    @Override
    public void fillRelateObj(EnterpriseUpdateDTO enterpriseUpdateDTO, Enterprise enterprise) {
        if (StringUtils.isNotBlank(enterpriseUpdateDTO.getParentId())) {
            Optional<Enterprise> enterpriseOptional = baseRepository.findById(Long.valueOf(enterpriseUpdateDTO.getParentId()));
            enterprise.setParent(enterpriseOptional.get());
        }

        if (StringUtils.isNotBlank(enterpriseUpdateDTO.getIndustryId())) {

        }
    }

    @Override
    public UniqueInfo checkUnique(EnterpriseUpdateDTO enterpriseUpdateDTO, Enterprise enterprise) {
        Integer count = StringUtils.isBlank(enterpriseUpdateDTO.getId()) ? entperiseQueryRepository.countByCodeOrName(enterpriseUpdateDTO.getName(), enterpriseUpdateDTO.getCode()) :
                entperiseQueryRepository.countByCodeOrNameAndIdNot(enterpriseUpdateDTO.getName(), enterpriseUpdateDTO.getCode(), Long.valueOf(enterpriseUpdateDTO.getId()));
        if (count != null) {
            return UniqueInfo.getUniqueInfo("企业名称或编码不能重复!");
        }
        return super.checkUnique(enterpriseUpdateDTO, enterprise);
    }
}
