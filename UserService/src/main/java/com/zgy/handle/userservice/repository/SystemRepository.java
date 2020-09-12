package com.zgy.handle.userservice.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.text.MessageFormat;

@NoRepositoryBean
@Primary
public interface SystemRepository<T> extends JpaRepository<T, Serializable>, JpaSpecificationExecutor<T> {




    /**
     * 字符串模糊查询
     * @param filed  查询字段
     * @param value 查询值
     * @return
     */
    default Specification<T> blurStrQuery(String filed, String value){
        return (root,query,builder) -> builder.like(root.get(filed),contains(value));
    }

    private static String contains(String expression){
        return MessageFormat.format("%{0}%",expression);
    }
}
