package com.zgy.handle.userService.controller.param;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userService.controller.SystemController;
import com.zgy.handle.userService.controller.param.convert.ParamMapper;
import com.zgy.handle.userService.model.parameter.Param;
import com.zgy.handle.userService.model.parameter.ParamDTO;
import com.zgy.handle.userService.model.parameter.ParamType;
import com.zgy.handle.userService.model.user.SelectDTO;
import com.zgy.handle.userService.service.SystemService;
import com.zgy.handle.userService.service.param.ParamService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "param")
public class ParamController extends SystemController<Param, ParamDTO> {
    private ParamService paramService;
    @Autowired
    private ParamMapper paramMapper;
    @Autowired
    public ParamController(ParamService paramService) {
        super(paramService);
        this.paramService = paramService;
    }

    @GetMapping(value = "getByCodeAndType")
    @ApiModelProperty(value = "根据code和type获取字段信息")
    public List<SelectDTO> findByCodeAndType(String code,ParamType paramType){
        return paramService.getParamSelectDTOByCode(code,paramType);
    }

    @Override
    @ApiOperation(value = "",hidden = true)
    public ResponseCode<List<ParamDTO>> list(Pageable pageable, ParamDTO dto) {
        return super.list(pageable, dto);
    }

    @GetMapping(value = "getSysParamList")
    @ApiOperation(value = "获取系统参数")
    public ResponseCode<List<ParamDTO>> sysParamList(@PageableDefault(page = 1,size = 10) Pageable pageable, ParamDTO dto){
        dto.setParamType(ParamType.SYS);
        return super.list(pageable,dto);
    }
    @GetMapping(value = "getBusParamList")
    @ApiOperation(value = "获取系统参数")
    public ResponseCode<List<ParamDTO>> busParamList(@PageableDefault(page = 1,size = 10) Pageable pageable,ParamDTO dto){
        dto.setParamType(ParamType.BUS);
        return super.list(pageable,dto);
    }

    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<Param> params) {
        return null;
    }

    @Override
    public List<ParamDTO> convertTtoU(List<Param> params) {
        return paramMapper.toParamDtoList(params);
    }

    @Override
    public ParamDTO convertTtoU(Param param) {
        return paramMapper.toParamDto(param);
    }

    @Override
    public Param convertUtoT(ParamDTO paramDTO) {
        return paramMapper.toParam(paramDTO);
    }
}
