package com.zgy.handle.knowledge.controller.comment.convert;

import com.zgy.handle.knowledge.model.common.ResourceComment;
import com.zgy.handle.knowledge.model.common.ResourceCommentDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResourceCommentMapper {
    ResourceCommentDTO toResourceCommentDTO(ResourceComment resourceComment);
    List<ResourceCommentDTO> toResourceCommentDTOs(List<ResourceComment> resourceCommentList);
    ResourceComment toResourceComment(ResourceCommentDTO resourceCommentDTO);
}
