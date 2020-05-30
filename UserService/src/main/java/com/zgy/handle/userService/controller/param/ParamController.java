package com.zgy.handle.userService.controller.param;

import com.zgy.handle.userService.controller.SystemController;
import com.zgy.handle.userService.controller.param.convert.ParamMapper;
import com.zgy.handle.userService.model.parameter.Param;
import com.zgy.handle.userService.model.parameter.ParamDTO;
import com.zgy.handle.userService.model.user.SelectDTO;
import com.zgy.handle.userService.service.SystemService;
import com.zgy.handle.userService.service.param.ParamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "param")
@Slf4j
public class  ParamController extends SystemController<Param, ParamDTO> {
    private ParamService paramService;
    @Autowired
    private ParamMapper paramMapper;
    @Autowired
    public ParamController(ParamService paramService) {
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
