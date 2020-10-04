package com.zgy.handle.userservice.repository.param.dict;

import com.zgy.handle.common.repository.base.QueryRepository;
import com.zgy.handle.userservice.model.parameter.Dict;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author a4423
 */
@Repository
public interface DictQueryRepository extends QueryRepository<Dict> {

    /**
     * 根据字典ID获取字典下的所有字典
     *
     * @param parentId
     * @return
     */
    List<Dict> findAllByParentId(Long parentId);

    /**
     * 获取所有的父字典
     *
     * @return
     */
    default Specification<Dict> rootDict() {
        return (Specification<Dict>) (root, query, criteriaBuilder) -> criteriaBuilder.isNull(root.get("parent").get("id"));
    }
}
