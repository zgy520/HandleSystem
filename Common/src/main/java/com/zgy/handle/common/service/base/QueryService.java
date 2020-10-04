package com.zgy.handle.common.service.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

/**
 * 查询类的基础
 */
public interface QueryService<T,U> extends BaseService<T> {
    /**
     * 获取所有数据
     * @return
     */
    List<T> findAll();

    /**
     * 根据id获取对象
     * @param id
     * @return
     */
    Optional<T> findById(Long id);

    /**
     * 根据id获取对象
     * @param id
     * @return
     */
    Object getOne(Long id);

    /**
     * 获取分页数据
     * @param pageable
     * @return
     */
    Page<T> findAll(Pageable pageable);

    /**
     * 动态查询
     * @param pageable
     * @param dto
     * @return
     */
    Page<T> findByDynamicQuery(Pageable pageable,U dto);

    /**
     * 获取权限的查询
     * @return
     */
    Specification<T> getPermissionQuery();
}
