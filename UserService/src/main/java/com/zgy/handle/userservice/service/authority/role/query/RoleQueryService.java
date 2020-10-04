package com.zgy.handle.userservice.service.authority.role.query;

import com.zgy.handle.common.service.base.QueryService;
import com.zgy.handle.userservice.model.authority.role.Role;
import com.zgy.handle.userservice.model.authority.role.RoleDTO;
import com.zgy.handle.userservice.model.common.TransferDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleQueryService extends QueryService<Role, RoleDTO> {
    /**
     * 根据角色ID获取所有的账户信息
     *
     * @param roleId
     * @return
     */
    List<TransferDTO> getAccountListByRoleId(Long roleId);
}
