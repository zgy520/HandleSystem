package com.zgy.handle.userService.service.authority.role.query;

import com.zgy.handle.userService.model.authority.role.Role;
import com.zgy.handle.userService.model.authority.role.RoleDTO;
import com.zgy.handle.userService.model.common.TransferDTO;
import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.model.user.SelectDTO;
import com.zgy.handle.userService.service.base.QueryService;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleQueryService extends QueryService<Role, RoleDTO> {
    /**
     * 根据角色ID获取所有的账户信息
     * @param roleId
     * @return
     */
    List<TransferDTO> getAccountListByRoleId(Long roleId);
}
