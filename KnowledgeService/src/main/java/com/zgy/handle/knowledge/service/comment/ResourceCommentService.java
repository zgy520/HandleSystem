package com.zgy.handle.knowledge.service.comment;

import com.zgy.handle.knowledge.model.common.ResourceComment;
import com.zgy.handle.knowledge.model.common.ResourceCommentDTO;
import com.zgy.handle.knowledge.repository.comment.ResourceCommentRepository;
import com.zgy.handle.knowledge.service.KnowledgeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ResourceCommentService extends KnowledgeService<ResourceComment, ResourceCommentDTO> {
    private ResourceCommentRepository resourceCommentRepository;
    @Autowired
    public ResourceCommentService(ResourceCommentRepository resourceCommentRepository) {
        super(resourceCommentRepository);
        this.resourceCommentRepository = resourceCommentRepository;
    }

    @Override
    public Page<ResourceComment> findByDynamicQuery(Pageable pageable, ResourceCommentDTO dto) {
        if (dto.getResourceType() == null || dto.getResourceId() == null){
            throw new EntityNotFoundException("请传入正确的资源类型和资源id");
        }
        Specification<ResourceComment> specification = Specification
                .where(ResourceCommentRepository.getByResourceType(dto.getResourceType()))
                .and(ResourceCommentRepository.getByResourceId(dto.getResourceId()))
                .and(StringUtils.isBlank(dto.getCreator())?null:ResourceCommentRepository.blurStrQuery("creator",dto.getCreator()))
                .and(StringUtils.isBlank(dto.getContent())?null:ResourceCommentRepository.blurStrQuery("content",dto.getContent()));
        return resourceCommentRepository.findAll(specification,pageable);
    }

    @Override
    public void beforeUpdate(ResourceCommentDTO resourceCommentDTO, ResourceComment resourceComment) {
        if (resourceCommentDTO.getResourceId() == null || resourceCommentDTO.getResourceType() == null){
            throw new EntityNotFoundException("请传入资源id和资源类型");
        }
        if (StringUtils.isBlank(resourceCommentDTO.getId())){
            resourceComment.setCreator(getCurUserName());
            if (StringUtils.isNotBlank(getPersonalId())){
                resourceComment.setPersonalId(Long.valueOf(getPersonalId()));
            }
        }else {
            resourceComment.setUpdator(getCurUserName());
        }
    }
}
