package com.zgy.handle.userservice.repository.param.param;

import com.zgy.handle.common.repository.base.QueryRepository;
import com.zgy.handle.userservice.model.parameter.Param;
import org.springframework.stereotype.Repository;

/**
 * @author a4423
 */
@Repository
public interface ParamQueryRepository extends QueryRepository<Param> {
    /**
     * 根据代码获取数量
     *
     * @param code
     * @return
     */
    Long countByCode(String code);

    /**
     * @param code
     * @param id
     * @return
     */
    Long countByCodeAndIdNot(String code, Long id);
}