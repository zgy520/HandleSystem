package com.zgy.handle.userService.controller.param.param.query;

import com.zgy.handle.userService.controller.base.QueryController;
import com.zgy.handle.userService.controller.param.convert.ParamMapper;
import com.zgy.handle.userService.model.parameter.Param;
import com.zgy.handle.userService.model.parameter.ParamDTO;
import com.zgy.handle.userService.model.user.SelectDTO;
import com.zgy.handle.userService.service.param.param.query.ParamQueryService;
import com.zgy.handle.userService.service.param.param.update.ParamUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "param/query")
@Slf4j
public class ParamQueryController extends QueryController<Param, ParamDTO> {
    @Autowired
    private ParamMapper paramMapper;
    private ParamQueryService paramQueryService;
    private ParamUpdateService paramUpdateService;
    public ParamQueryController(ParamUpdateService paramUpdateService, ParamQueryService paramQueryService) {
        super(paramUpdateService, paramQueryService);
        this.paramQueryService = paramQueryService;
        this.paramQueryService = paramQueryService;
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
