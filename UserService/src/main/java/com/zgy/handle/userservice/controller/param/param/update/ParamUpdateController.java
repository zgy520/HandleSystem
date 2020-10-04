package com.zgy.handle.userservice.controller.param.param.update;

import com.zgy.handle.common.controller.base.BaseUpdateController;
import com.zgy.handle.userservice.controller.param.convert.ParamMapper;
import com.zgy.handle.userservice.model.parameter.Param;
import com.zgy.handle.userservice.model.parameter.ParamDTO;
import com.zgy.handle.userservice.service.param.param.query.ParamQueryService;
import com.zgy.handle.userservice.service.param.param.update.ParamUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "paramInfo/update")
@Slf4j
public class ParamUpdateController extends BaseUpdateController<Param, ParamDTO> {
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
