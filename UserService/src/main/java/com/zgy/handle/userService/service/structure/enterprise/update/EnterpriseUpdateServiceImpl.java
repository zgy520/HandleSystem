package com.zgy.handle.userService.service.structure.enterprise.update;

import com.zgy.handle.userService.model.structure.Enterprise;
import com.zgy.handle.userService.model.structure.EnterpriseDTO;
import com.zgy.handle.userService.repository.structure.enterprise.EnterpriseUpdateRepository;
import com.zgy.handle.userService.service.base.impl.UpdateServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseUpdateServiceImpl extends UpdateServiceImpl<Enterprise, EnterpriseDTO> implements EnterpriseUpdateService {
    private EnterpriseUpdateRepository enterpriseUpdateRepository;
    public EnterpriseUpdateServiceImpl(EnterpriseUpdateRepository enterpriseUpdateRepository) {
        super(enterpriseUpdateRepository);
        this.enterpriseUpdateRepository = enterpriseUpdateRepository;
    }
}
