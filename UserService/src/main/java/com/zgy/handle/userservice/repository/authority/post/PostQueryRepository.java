package com.zgy.handle.userservice.repository.authority.post;

import com.zgy.handle.common.repository.base.QueryRepository;
import com.zgy.handle.userservice.model.authority.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author a4423
 */
@Repository
public interface PostQueryRepository extends QueryRepository<Post> {

    /**
     * 根据代码或名称获取数量
     * @param code
     * @param name
     * @return
     */
    Long countByCodeOrName(String code,String name);

    /**
     *
     * @param code
     * @param id
     * @return
     */
    Long countByCodeAndIdNot(String code, Long id);

    List<Post> findByIdIn(List<Long> postIdList);
}