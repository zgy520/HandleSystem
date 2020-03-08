package com.zgy.handle.knowledge.repository.solution;

import com.zgy.handle.knowledge.model.solution.Solution;
import com.zgy.handle.knowledge.repository.KnowledgeRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;

@Repository
public interface SolutionRepository extends KnowledgeRepository<Solution>, JpaSpecificationExecutor<Solution> {

    static Specification<Solution> findByCatalogName(String catalogName){
        return (root,query,builder) -> builder.like(root.get("catalog").get("name"),contains(catalogName));
    }

    /**
     * 字符串模糊查询
     * @param filed  查询字段
     * @param value 查询值
     * @return
     */
    static Specification<Solution> blurStrQuery(String filed, String value){
        return (root,query,builder) -> builder.like(root.get(filed),contains(value));
    }

    static String contains(String expression){
        return MessageFormat.format("%{0}%",expression);
    }
}
