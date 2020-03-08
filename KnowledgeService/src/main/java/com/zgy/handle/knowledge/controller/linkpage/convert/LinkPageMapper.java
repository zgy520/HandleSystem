package com.zgy.handle.knowledge.controller.linkpage.convert;

import com.zgy.handle.knowledge.model.linkpage.LinkPage;
import com.zgy.handle.knowledge.model.linkpage.LinkPageDTO;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LinkPageMapper {
    @Mapping(source = "catalog.name",target = "catalogName")
    @Mapping(source = "catalog.id",target = "catalogId")
    LinkPageDTO toLinkPageDTO(LinkPage linkPage);
    List<LinkPageDTO> toLinkPageDTOS(List<LinkPage> linkPageList);
    @InheritConfiguration
    LinkPage toLinkPage(LinkPageDTO linkPageDTO);
}
