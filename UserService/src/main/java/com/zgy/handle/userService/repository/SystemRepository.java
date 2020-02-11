package com.zgy.handle.userService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SystemRepository<T> extends JpaRepository<T,Long> {
}
