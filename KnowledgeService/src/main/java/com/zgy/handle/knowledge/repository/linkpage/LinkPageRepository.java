package com.zgy.handle.knowledge.repository.linkpage;

import com.zgy.handle.knowledge.model.linkpage.LinkPage;
import com.zgy.handle.knowledge.repository.KnowledgeRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;

@Repository
public interface LinkPageRepository extends KnowledgeRepository<LinkPage>, JpaSpecificationExecutor<LinkPage> {

    static Specification<LinkPage> findByCatalogName(String catalogName){
        return (root,query,builder) -> builder.like(root.get("catalog").get("name"),contains(catalogName));
    }
    /**
     * 字符串模糊查询
     * @param filed  查询字段
     * @param value 查询值
     * @return
     */
    static Specification<LinkPage> blurStrQuery(String filed, String value){
        return (root,query,builder) -> builder.like(root.get(filed),contains(value));
    }

    static String contains(String expression){
        return MessageFormat.format("%{0}%",expression);
    }
}
