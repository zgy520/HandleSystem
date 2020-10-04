package com.zgy.handle.userservice.repository.menu;

import com.zgy.handle.common.repository.base.QueryRepository;
import com.zgy.handle.userservice.model.menu.RoleMenuButton;

import java.util.List;

/**
 * @author: a4423
 * @date: 2020/9/12 20:23
 */
public interface RoleMenuButtonQueryRepository extends QueryRepository<RoleMenuButton> {
    /**
     * 根据角色ID获取列表
     * @param roleId
     * @return
     */
    List<RoleMenuButton> findByRoleMenuButtonPK_RoleId(Long roleId);
    /**
     * 根据角色ID和菜单ID获取列表
     * @param roleId 角色ID
     * @param menuId
     * @return
     */
    List<RoleMenuButton> findByRoleMenuButtonPK_RoleIdAndRoleMenuButtonPK_MenuId(Long roleId,Long menuId);


}
