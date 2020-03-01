package com.zgy.handle.knowledge.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.text.MessageFormat;

@NoRepositoryBean
@Primary
public interface KnowledgeRepository<T> extends JpaRepository<T, Serializable> {
}
