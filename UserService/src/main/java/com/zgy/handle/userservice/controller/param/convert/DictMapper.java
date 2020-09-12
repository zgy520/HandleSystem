package com.zgy.handle.userservice.controller.param.convert;

import com.zgy.handle.userservice.model.parameter.Dict;
import com.zgy.handle.userservice.model.parameter.DictDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DictMapper {
    Dict toDict(DictDTO dictDTO);
    @Mapping(source = "parent.id", target = "parentId")
    DictDTO toDictDto(Dict dict);
    List<DictDTO> toDictDtoList(List<Dict> dictList);
}
