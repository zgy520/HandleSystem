package com.zgy.handle.userService.controller.param.convert;

import com.zgy.handle.userService.model.parameter.Param;
import com.zgy.handle.userService.model.parameter.ParamDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParamMapper {
    Param toParam(ParamDTO paramDTO);
    ParamDTO toParamDto(Param param);
    List<Param> toParamList(List<ParamDTO> paramDTOList);
    List<ParamDTO> toParamDTOList(List<Param> paramList);
}
