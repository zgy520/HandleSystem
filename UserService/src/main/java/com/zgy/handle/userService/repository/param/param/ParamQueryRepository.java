package com.zgy.handle.userService.repository.param.param;

import com.zgy.handle.userService.model.parameter.Param;
import com.zgy.handle.userService.repository.base.QueryRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParamQueryRepository extends QueryRepository<Param> {
    /**
     * 根据代码获取数量
     * @param code
     * @return
     */
    Long countByCode(String code);

    /**
     *
     * @param code
     * @param id
     * @return
     */
    Long countByCodeAndIdNot(String code, Long id);
}
