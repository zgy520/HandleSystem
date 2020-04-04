package com.zgy.handle.knowledge.repository.comment;

import com.zgy.handle.knowledge.model.common.ResourceComment;
import com.zgy.handle.knowledge.model.common.ResourceType;
import com.zgy.handle.knowledge.repository.KnowledgeRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;

@Repository
public interface ResourceCommentRepository extends KnowledgeRepository<ResourceComment>, JpaSpecificationExecutor<ResourceComment> {

    static Specification<ResourceComment> blurStrQuery(String filed, String value){
        return (root,query,builder) -> builder.like(root.get(filed),contains(value));
    }

    static Specification<ResourceComment> getByResourceId(Long resourceId){
        return (root,query,builder) -> builder.equal(root.get("resourceId"),resourceId);
    }

    static Specification<ResourceComment> getByResourceType(ResourceType resourceType){
        return (root,query,builder) -> builder.equal(root.get("resourceType"),resourceType);
    }

    static String contains(String expression){
        return MessageFormat.format("%{0}%",expression);
    }
}
