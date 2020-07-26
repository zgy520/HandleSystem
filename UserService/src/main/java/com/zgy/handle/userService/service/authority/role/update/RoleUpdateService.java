package com.zgy.handle.userService.service.authority.role.update;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userService.model.authority.role.Role;
import com.zgy.handle.userService.model.authority.role.RoleDTO;
import com.zgy.handle.userService.service.base.UpdateService;

public interface RoleUpdateService extends UpdateService<Role, RoleDTO> {
    String relateUser(Long roleId, String selectedUserList);
}