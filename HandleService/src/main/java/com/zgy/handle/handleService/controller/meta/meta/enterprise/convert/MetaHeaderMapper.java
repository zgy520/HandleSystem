package com.zgy.handle.handleService.controller.meta.meta.enterprise.convert;

import com.zgy.handle.handleService.model.meta.dto.structure.MetaHeaderDTO;
import com.zgy.handle.handleService.model.meta.structure.enterprise.MetaHeader;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MetaHeaderMapper {
    @Mapping(source = "identityNum",target = "header.identityNum")
    @Mapping(source = "version",target = "header.version")
    @Mapping(source = "name",target = "header.name")
    @Mapping(source = "alias",target = "header.alias")
    @Mapping(source = "state",target = "header.state")
    MetaHeader toMetaHeader(MetaHeaderDTO metaHeaderDTO);
    @Mapping(source = "header.identityNum",target = "identityNum")
    @Mapping(source = "header.version",target = "version")
    @Mapping(source = "header.name",target = "name")
    @Mapping(source = "header.alias",target = "alias")
    @Mapping(source = "header.state",target = "state")
    @Mapping(source = "parent.id",target = "parentId")
    MetaHeaderDTO toMetaHeaderDTO(MetaHeader metaHeader);
    List<MetaHeaderDTO> toMetaHeaderDTOS(List<MetaHeader> metaHeaders);
}
