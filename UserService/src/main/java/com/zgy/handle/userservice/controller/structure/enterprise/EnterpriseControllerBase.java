package com.zgy.handle.userservice.controller.structure.enterprise;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userservice.controller.BaseSystemController;
import com.zgy.handle.userservice.controller.structure.convert.EnterpriseMapper;
import com.zgy.handle.userservice.model.structure.Enterprise;
import com.zgy.handle.userservice.model.structure.EnterpriseDTO;
import com.zgy.handle.userservice.model.user.SelectDTO;
import com.zgy.handle.userservice.service.structure.enterprise.EnterpriseServiceBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "enterprise")
@Slf4j
public class EnterpriseControllerBase extends BaseSystemController<Enterprise,EnterpriseDTO> {
    private EnterpriseServiceBase enterpriseService;
    @Autowired
    private EnterpriseMapper enterpriseMapper;

    public EnterpriseControllerBase(EnterpriseServiceBase enterpriseService) {
        super(enterpriseService);
        this.enterpriseService = enterpriseService;
    }

    @Override
    public ResponseCode<List<EnterpriseDTO>> list(Pageable pageable, EnterpriseDTO dto) {
        ResponseCode<List<EnterpriseDTO>> responseCode = ResponseCode.sucess();
        List<Enterprise> enterpriseList = enterpriseService.findAll();
        List<EnterpriseDTO> enterpriseDTOList = enterpriseMapper.toEnterpriseDTOs(enterpriseList);
        responseCode.setData(enterpriseService.getEnterpriseDtoList(enterpriseDTOList));
        return responseCode;
    }

    @Override
    public List<EnterpriseDTO> convertTtoU(List<Enterprise> enterpriseList) {
        return enterpriseMapper.toEnterpriseDTOs(enterpriseList);
    }

    @Override
    public EnterpriseDTO convertTtoU(Enterprise enterprise) {
        return enterpriseMapper.toEnterpriseDTO(enterprise);
    }

    @Override
    public Enterprise convertUtoT(EnterpriseDTO enterpriseDTO) {
        return enterpriseMapper.toEnterprise(enterpriseDTO);
    }

    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<Enterprise> enterpriseList) {
        List<SelectDTO> selectDTOList = new ArrayList<>();
        enterpriseList.stream().forEach(enterprise -> {
            SelectDTO selectDTO = new SelectDTO(enterprise.getId().toString(),enterprise.getName(),enterprise.getId().toString());
            selectDTOList.add(selectDTO);
        });
        return selectDTOList;
    }

    /**
     * 获取所有的企业列表，用于下拉框
     * @return
     */
    @GetMapping(value = "getEnterpriseList")
    public ResponseCode<List<SelectDTO>> getEnerpriseList(){
        ResponseCode<List<SelectDTO>> responseCode = ResponseCode.sucess();
        List<Enterprise> enterpriseList = enterpriseService.findAll();
        List<SelectDTO> selectDTOList = new ArrayList<>();
        enterpriseList.stream().forEach(enterprise -> {
            SelectDTO selectDTO = new SelectDTO(enterprise.getId().toString(),enterprise.getName(),enterprise.getId().toString());
            selectDTOList.add(selectDTO);
        });
        responseCode.setData(selectDTOList);
        return responseCode;
    }
}
