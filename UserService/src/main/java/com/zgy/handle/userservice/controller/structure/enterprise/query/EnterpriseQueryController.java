package com.zgy.handle.userservice.controller.structure.enterprise.query;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userservice.controller.base.BaseQueryController;
import com.zgy.handle.userservice.controller.structure.enterprise.convert.EnterpriseQueryMapper;
import com.zgy.handle.userservice.model.dto.structure.EnterpriseQueryDTO;
import com.zgy.handle.userservice.model.structure.Enterprise;
import com.zgy.handle.userservice.model.user.SelectDTO;
import com.zgy.handle.userservice.service.structure.enterprise.query.EnterpriseQueryService;
import com.zgy.handle.userservice.service.structure.enterprise.update.EnterpriseUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "enterprise/query")
public class EnterpriseQueryController extends BaseQueryController<Enterprise, EnterpriseQueryDTO> {

    @Autowired
    private EnterpriseQueryMapper enterpriseQueryMapper;
    private EnterpriseQueryService enterpriseQueryService;
    private EnterpriseUpdateService enterpriseUpdateService;

    @Autowired
    public EnterpriseQueryController(EnterpriseUpdateService enterpriseUpdateService, EnterpriseQueryService enterpriseQueryService) {
        super(enterpriseUpdateService, enterpriseQueryService);
        this.enterpriseQueryService = enterpriseQueryService;
        this.enterpriseUpdateService = enterpriseUpdateService;
    }


    @Override
    public ResponseCode<List<EnterpriseQueryDTO>> list(Pageable pageable, EnterpriseQueryDTO dto) {
        ResponseCode<List<EnterpriseQueryDTO>> responseCode = ResponseCode.sucess();
        List<Enterprise> enterpriseList = enterpriseQueryService.findBySpecification(dto);
        List<EnterpriseQueryDTO> enterpriseQueryDTOList = enterpriseQueryMapper.toEnterpriseQueryDTOList(enterpriseList);
        responseCode.setData(enterpriseQueryService.getTreeEnterpriseQueryDto(enterpriseQueryDTOList));
        return responseCode;
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
    public List<EnterpriseQueryDTO> convertTtoU(List<Enterprise> enterprises) {
        return enterpriseQueryMapper.toEnterpriseQueryDTOList(enterprises);
    }

    @Override
    public EnterpriseQueryDTO convertTtoU(Enterprise enterprise) {
        return enterpriseQueryMapper.toEnterpriseQueryDTO(enterprise);
    }

    @Override
    public Enterprise convertUtoT(EnterpriseQueryDTO enterpriseDTO) {
        return enterpriseQueryMapper.toEnterprise(enterpriseDTO);
    }
}
