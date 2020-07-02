package com.zgy.handle.userService.repository.base;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface QueryRepository<T> extends BaseRepository<T>, JpaSpecificationExecutor<T> {
}
