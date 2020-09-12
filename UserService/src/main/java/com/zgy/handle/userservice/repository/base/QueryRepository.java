package com.zgy.handle.userservice.repository.base;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface QueryRepository<T> extends BaseRepository<T>, JpaSpecificationExecutor<T> {

    /**
     * 字段相等
     * @param field
     * @param value
     * @return
     */
    default Specification<T> fieldLike(String field, String value){
        return (Specification<T>) (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(field),"%" + value + "%");
    }

    /**
     * 字段相等
     * @param field
     * @param value
     * @return
     */
    default Specification<T> fieldEquals(String field, String value){
        return (Specification<T>) (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(field),value);
    }

    /**
     * 动态查询的in语句
     * @param field
     * @param valueList
     * @return
     */
    default Specification<T> fieldIn(String field,String valueList){
        return (Specification<T>) (root, query, criteriaBuilder) -> root.get(field).in(valueList);
    }
}
