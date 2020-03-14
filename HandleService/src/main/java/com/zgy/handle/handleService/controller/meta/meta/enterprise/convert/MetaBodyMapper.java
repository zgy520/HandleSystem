package com.zgy.handle.handleService.controller.meta.meta.enterprise.convert;

import com.zgy.handle.handleService.model.meta.dto.structure.MetaBodyDTO;
import com.zgy.handle.handleService.model.meta.structure.enterprise.MetaBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MetaBodyMapper {
    @Mapping(source = "name",target = "body.name")
    @Mapping(source = "description",target = "body.description")
    @Mapping(source = "colType",target = "body.colType")
    @Mapping(source = "fieldLen",target = "body.fieldLen")
    @Mapping(source = "headerId",target = "metaHeader.id")
    MetaBody toMetaBody(MetaBodyDTO metaBodyDTO);
    @Mapping(source = "body.name",target = "name")
    @Mapping(source = "body.description",target = "description")
    @Mapping(source = "body.colType",target = "colType")
    @Mapping(source = "body.fieldLen",target = "fieldLen")
    @Mapping(source = "metaHeader.id",target = "headerId")
    MetaBodyDTO toMetaBodyDTO(MetaBody metaBody);
    List<MetaBodyDTO> toMetaBodyDTOS(List<MetaBody> metaBodies);

    List<MetaBody> toMetaBodys(List<MetaBodyDTO> metaBodyDTOList);
}
