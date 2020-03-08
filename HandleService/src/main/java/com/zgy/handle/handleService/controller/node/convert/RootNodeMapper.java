package com.zgy.handle.handleService.controller.node.convert;

import com.zgy.handle.handleService.model.node.RootNode;
import com.zgy.handle.handleService.model.node.RootNodeDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RootNodeMapper {
    RootNode toRootNode(RootNodeDTO rootNodeDTO);
    RootNodeDTO toRootNodeDTO(RootNode rootNode);
    List<RootNodeDTO> toRootNodeDTOS(List<RootNode> rootNodes);
}
