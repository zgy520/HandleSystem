package com.zgy.handle.handleService.controller.meta.meta.industry.convert;

import com.zgy.handle.handleService.model.meta.dto.structure.IndustryMetaBodyDTO;
import com.zgy.handle.handleService.model.meta.structure.industry.IndustryMetaBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IndustryMetaBodyMapper {
    @Mapping(source = "name",target = "body.name")
    @Mapping(source = "description",target = "body.description")
    @Mapping(source = "colType",target = "body.colType")
    @Mapping(source = "fieldLen",target = "body.fieldLen")
    @Mapping(source = "headerId",target = "industryMetaHeader.id")
    IndustryMetaBody toIndustryMetaBody(IndustryMetaBodyDTO industryMetaBodyDTO);
    @Mapping(source = "body.name",target = "name")
    @Mapping(source = "body.description",target = "description")
    @Mapping(source = "body.colType",target = "colType")
    @Mapping(source = "body.fieldLen",target = "fieldLen")
    @Mapping(source = "industryMetaHeader.id",target = "headerId")
    IndustryMetaBodyDTO toIndustryMetaBodyDTO(IndustryMetaBody industryMetaBody);
    List<IndustryMetaBodyDTO> toIndustryMetaBodyDTOS(List<IndustryMetaBody> industryMetaBodies);

    List<IndustryMetaBody> toIndustryMetaBodS(List<IndustryMetaBodyDTO> industryMetaBodies);
}
