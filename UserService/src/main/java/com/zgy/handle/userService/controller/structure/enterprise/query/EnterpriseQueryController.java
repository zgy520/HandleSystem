package com.zgy.handle.userService.controller.structure.enterprise.query;

import com.zgy.handle.userService.controller.SystemController;
import com.zgy.handle.userService.controller.base.QueryController;
import com.zgy.handle.userService.model.structure.Enterprise;
import com.zgy.handle.userService.model.structure.EnterpriseDTO;
import com.zgy.handle.userService.model.user.SelectDTO;
import com.zgy.handle.userService.service.SystemService;
import com.zgy.handle.userService.service.base.QueryService;
import com.zgy.handle.userService.service.base.UpdateService;
import com.zgy.handle.userService.service.structure.enterprise.query.EnterpriseQueryService;
import com.zgy.handle.userService.service.structure.enterprise.update.EnterpriseUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "enterprise/query")
public class EnterpriseQueryController extends QueryController<Enterprise, EnterpriseDTO> {

    private EnterpriseQueryService enterpriseQueryService;
    private EnterpriseUpdateService enterpriseUpdateService;
    @Autowired
    public EnterpriseQueryController(EnterpriseUpdateService enterpriseUpdateService, EnterpriseQueryService enterpriseQueryService) {
        super(enterpriseUpdateService, enterpriseQueryService);
        this.enterpriseQueryService = enterpriseQueryService;
        this.enterpriseUpdateService = enterpriseUpdateService;
    }

    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<Enterprise> enterprises) {
        List<SelectDTO> selectDTOList = new ArrayList<>();
        enterprises.stream().forEach(enterprise -> {
            SelectDTO selectDTO = new SelectDTO();
            selectDTO.setLabel(enterprise.getName());
            selectDTO.setValue(enterprise.getId().toString());
            selectDTO.setKey(enterprise.getId().toString());
            selectDTOList.add(selectDTO);
        });
        return selectDTOList;
    }

    @Override
    public List<EnterpriseDTO> convertTtoU(List<Enterprise> enterprises) {
        return null;
    }

    @Override
    public EnterpriseDTO convertTtoU(Enterprise enterprise) {
        return null;
    }

    @Override
    public Enterprise convertUtoT(EnterpriseDTO enterpriseDTO) {
        return null;
    }
}