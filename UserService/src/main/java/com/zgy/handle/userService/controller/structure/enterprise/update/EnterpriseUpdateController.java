package com.zgy.handle.userService.controller.structure.enterprise.update;

import com.zgy.handle.userService.controller.base.UpdateController;
import com.zgy.handle.userService.controller.structure.enterprise.convert.EnterpriseUpdateMapper;
import com.zgy.handle.userService.model.dto.structure.EnterpriseUpdateDTO;
import com.zgy.handle.userService.model.structure.Enterprise;
import com.zgy.handle.userService.service.structure.enterprise.query.EnterpriseQueryService;
import com.zgy.handle.userService.service.structure.enterprise.update.EnterpriseUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "enterprise/query")
public class EnterpriseUpdateController extends UpdateController<Enterprise, EnterpriseUpdateDTO> {

    @Autowired
    private EnterpriseUpdateMapper enterpriseUpdateMapper;
    private EnterpriseQueryService enterpriseQueryService;
    private EnterpriseUpdateService enterpriseUpdateService;

    @Autowired
    public EnterpriseUpdateController(EnterpriseUpdateService enterpriseUpdateService, EnterpriseQueryService enterpriseQueryService) {
        super(enterpriseUpdateService, enterpriseQueryService);
        this.enterpriseQueryService = enterpriseQueryService;
        this.enterpriseUpdateService = enterpriseUpdateService;
    }

    @Override
    public Enterprise convertUtoT(EnterpriseUpdateDTO enterpriseUpdateDTO) {
        return enterpriseUpdateMapper.toEnterprise(enterpriseUpdateDTO);
    }
}
