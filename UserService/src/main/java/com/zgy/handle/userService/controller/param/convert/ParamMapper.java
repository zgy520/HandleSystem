package com.zgy.handle.userService.controller.param.convert;

import com.zgy.handle.userService.model.parameter.Param;
import com.zgy.handle.userService.model.parameter.ParamDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParamMapper {
    @Mapping(source = "parent.id",target = "parentId")
    ParamDTO toParamDto(Param param);
    Param toParam(ParamDTO paramDTO);
    List<ParamDTO> toParamDtoList(List<Param> paramList);
}
