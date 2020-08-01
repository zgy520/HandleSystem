package com.zgy.handle.userService.controller.param.param.update;

import com.zgy.handle.userService.controller.base.UpdateController;
import com.zgy.handle.userService.controller.param.convert.ParamMapper;
import com.zgy.handle.userService.model.parameter.Param;
import com.zgy.handle.userService.model.parameter.ParamDTO;
import com.zgy.handle.userService.service.param.param.query.ParamQueryService;
import com.zgy.handle.userService.service.param.param.update.ParamUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "paramInfo/update")
@Slf4j
public class ParamUpdateController extends UpdateController<Param, ParamDTO> {
    @Autowired
    private ParamMapper paramMapper;
    private ParamQueryService paramQueryService;
    private ParamUpdateService paramUpdateService;
    public ParamUpdateController(ParamUpdateService paramUpdateService, ParamQueryService paramQueryService) {
        super(paramUpdateService, paramQueryService);
        this.paramQueryService = paramQueryService;
        this.paramQueryService = paramQueryService;
    }

    @Override
    public Param convertUtoT(ParamDTO paramDTO) {
        return paramMapper.toParam(paramDTO);
    }
}