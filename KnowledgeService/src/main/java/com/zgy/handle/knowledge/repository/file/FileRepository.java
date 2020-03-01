package com.zgy.handle.knowledge.repository.file;

import com.zgy.handle.knowledge.model.file.File;
import com.zgy.handle.knowledge.repository.KnowledgeRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;

@Repository
public interface FileRepository extends KnowledgeRepository<File>, JpaSpecificationExecutor<File> {

    /**
     * 字符串模糊查询
     * @param filed  查询字段
     * @param value 查询值
     * @return
     */
    static Specification<File> blurStrQuery(String filed, String value){
        return (root,query,builder) -> builder.like(root.get(filed),contains(value));
    }

    static String contains(String expression){
        return MessageFormat.format("%{0}%",expression);
    }
}
