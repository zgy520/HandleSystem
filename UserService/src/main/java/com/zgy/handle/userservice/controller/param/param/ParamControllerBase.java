package com.zgy.handle.userservice.controller.param.param;

import com.zgy.handle.userservice.controller.BaseSystemController;
import com.zgy.handle.userservice.controller.param.convert.ParamMapper;
import com.zgy.handle.userservice.model.parameter.Param;
import com.zgy.handle.userservice.model.parameter.ParamDTO;
import com.zgy.handle.userservice.model.user.SelectDTO;
import com.zgy.handle.userservice.service.param.param.ParamServiceBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "param")
@Slf4j
public class ParamControllerBase extends BaseSystemController<Param, ParamDTO> {
    private ParamServiceBase paramService;
    @Autowired
    private ParamMapper paramMapper;
    @Autowired
    public ParamControllerBase(ParamServiceBase paramService) {
        super(paramService);
        this.paramService = paramService;
    }

    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<Param> paramList) {
        return null;
    }

    @Override
    public List<ParamDTO> convertTtoU(List<Param> paramList) {
        return paramMapper.toParamDTOList(paramList);
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
