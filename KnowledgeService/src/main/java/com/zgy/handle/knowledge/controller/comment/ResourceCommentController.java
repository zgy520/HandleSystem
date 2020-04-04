package com.zgy.handle.knowledge.controller.comment;

import com.zgy.handle.knowledge.controller.KnowledgeController;
import com.zgy.handle.knowledge.controller.comment.convert.ResourceCommentMapper;
import com.zgy.handle.knowledge.model.SelectDTO;
import com.zgy.handle.knowledge.model.common.ResourceComment;
import com.zgy.handle.knowledge.model.common.ResourceCommentDTO;
import com.zgy.handle.knowledge.service.KnowledgeService;
import com.zgy.handle.knowledge.service.comment.ResourceCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "rcc")
@Slf4j
public class ResourceCommentController extends KnowledgeController<ResourceComment, ResourceCommentDTO> {
    private ResourceCommentService resourceCommentService;
    @Autowired
    private ResourceCommentMapper resourceCommentMapper;
    @Autowired
    public ResourceCommentController(ResourceCommentService resourceCommentService) {
        super(resourceCommentService);
        this.resourceCommentService = resourceCommentService;
    }

    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<ResourceComment> resourceComments) {
        return null;
    }

    @Override
    public List<ResourceCommentDTO> convertTtoU(List<ResourceComment> resourceComments) {
        return resourceCommentMapper.toResourceCommentDTOs(resourceComments);
    }

    @Override
    public ResourceCommentDTO convertTtoU(ResourceComment resourceComment) {
        return resourceCommentMapper.toResourceCommentDTO(resourceComment);
    }

    @Override
    public ResourceComment convertUtoT(ResourceCommentDTO resourceCommentDTO) {
        return resourceCommentMapper.toResourceComment(resourceCommentDTO);
    }
}
