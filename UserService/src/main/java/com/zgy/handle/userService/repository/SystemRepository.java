package com.zgy.handle.userService.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.text.MessageFormat;
import java.util.List;
import java.util.Set;

@NoRepositoryBean
public interface SystemRepository<T> extends JpaRepository<T,Long> {

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
