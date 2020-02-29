package com.zgy.handle.knowledge.controller.catalog.convert;

import com.zgy.handle.knowledge.model.catalog.Catalog;
import com.zgy.handle.knowledge.model.catalog.CatalogDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatalogMapper {
    @Mapping(source = "parent.id",target = "parentId")
    CatalogDTO toCatalogDTO(Catalog catalog);
    List<CatalogDTO> toCatalogDTOS(List<Catalog> catalogList);
    Catalog toCatalog(CatalogDTO catalogDTO);
}
