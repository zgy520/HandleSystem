package com.zgy.handle.userservice.controller.param.convert;

import com.zgy.handle.userservice.model.parameter.Param;
import com.zgy.handle.userservice.model.parameter.ParamDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParamMapper {
    Param toParam(ParamDTO paramDTO);
    @Mapping(source = "code",target = "code")
    @Mapping(source = "value",target = "value")
    ParamDTO toParamDto(Param param);
    List<Param> toParamList(List<ParamDTO> paramDTOList);
    List<ParamDTO> toParamDTOList(List<Param> paramList);
}
