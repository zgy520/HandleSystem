package com.zgy.handle.userservice.repository.authority.role;

import com.zgy.handle.common.repository.base.QueryRepository;
import com.zgy.handle.userservice.model.authority.role.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleQueryRepository extends QueryRepository<Role> {


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
}
