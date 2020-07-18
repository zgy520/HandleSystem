package com.zgy.handle.userService.repository.authority.role;

import com.zgy.handle.userService.model.authority.role.Role;
import com.zgy.handle.userService.repository.base.QueryRepository;
import org.springframework.stereotype.Repository;

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
