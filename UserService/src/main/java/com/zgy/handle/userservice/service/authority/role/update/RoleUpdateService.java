package com.zgy.handle.userservice.service.authority.role.update;

import com.zgy.handle.userservice.model.authority.role.Role;
import com.zgy.handle.userservice.model.authority.role.RoleDTO;
import com.zgy.handle.userservice.service.base.UpdateService;

public interface RoleUpdateService extends UpdateService<Role, RoleDTO> {
    String relateUser(Long roleId, String selectedUserList);
}
