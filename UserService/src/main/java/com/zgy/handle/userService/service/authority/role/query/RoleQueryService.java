package com.zgy.handle.userService.service.authority.role.query;

import com.zgy.handle.userService.model.authority.role.Role;
import com.zgy.handle.userService.model.authority.role.RoleDTO;
import com.zgy.handle.userService.service.base.QueryService;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleQueryService extends QueryService<Role, RoleDTO> {
}
