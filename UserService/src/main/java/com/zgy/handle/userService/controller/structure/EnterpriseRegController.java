package com.zgy.handle.userService.controller.structure;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userService.controller.SystemController;
import com.zgy.handle.userService.controller.UserController;
import com.zgy.handle.userService.controller.structure.convert.EnterpriseRegMapper;
import com.zgy.handle.userService.model.structure.Enterprise;
import com.zgy.handle.userService.model.structure.EnterpriseRegDTO;
import com.zgy.handle.userService.model.structure.StatusType;
import com.zgy.handle.userService.model.user.SelectDTO;
import com.zgy.handle.userService.service.SystemService;
import com.zgy.handle.userService.service.structure.EnterpriseRegService;
import com.zgy.handle.userService.service.structure.EnterpriseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "enterprise/reg")
@Slf4j
@Api(tags = "企业注册")
public class EnterpriseRegController extends SystemController<Enterprise, EnterpriseRegDTO> {
    private EnterpriseRegService enterpriseService;
    @Autowired
    private EnterpriseRegMapper enterpriseRegMapper;
    public EnterpriseRegController(EnterpriseRegService enterpriseService) {
        super(enterpriseService);
        this.enterpriseService = enterpriseService;
    }

    @ApiOperation("获取用户所在的企业信息")
    @GetMapping(value = "getSelfEnterprise")
    public ResponseCode<EnterpriseRegDTO> getSelfEnterprise(){
        ResponseCode<EnterpriseRegDTO> regDTOResponseCode = ResponseCode.sucess();
        Optional<Enterprise> optionalEnterprise = enterpriseService.getSelfEnterprise();
        if (optionalEnterprise.isPresent()){
            regDTOResponseCode.setData(enterpriseRegMapper.toEnterpriseRegDTO(optionalEnterprise.get()));
        }
        return regDTOResponseCode;
    }
    @ApiOperation("企业前缀填写")
    @PostMapping(value = "fillEnterpriseFix")
    public ResponseCode<EnterpriseRegDTO> fillEnterprisePrefix(Long enterpriseId,String prefix){
        ResponseCode<EnterpriseRegDTO> regDTOResponseCode = ResponseCode.sucess();
        regDTOResponseCode.setData(enterpriseRegMapper.toEnterpriseRegDTO(enterpriseService.fillEnterprisePrefix(enterpriseId,prefix)));
        return regDTOResponseCode;
    }

    @ApiOperation("企业授权和审核")
    @PostMapping(value = "stateChange")
    public ResponseCode<EnterpriseRegDTO> stateChange(Long enterpriseId, StatusType statusType, String statusValue){
        ResponseCode<EnterpriseRegDTO> regDTOResponseCode = ResponseCode.sucess();
        regDTOResponseCode.setData(enterpriseRegMapper.toEnterpriseRegDTO(enterpriseService.statusChange(enterpriseId,statusType,statusValue)));
        return regDTOResponseCode;
    }
    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<Enterprise> enterpriseList) {
        return null;
    }

    @Override
    public List<EnterpriseRegDTO> convertTtoU(List<Enterprise> enterpriseList) {
        return enterpriseRegMapper.toEnterpriseRegDTOs(enterpriseList);
    }

    @Override
    public EnterpriseRegDTO convertTtoU(Enterprise enterprise) {
        return enterpriseRegMapper.toEnterpriseRegDTO(enterprise);
    }

    @Override
    public Enterprise convertUtoT(EnterpriseRegDTO enterpriseRegDTO) {
        return enterpriseRegMapper.toEnterprise(enterpriseRegDTO);
    }
}
