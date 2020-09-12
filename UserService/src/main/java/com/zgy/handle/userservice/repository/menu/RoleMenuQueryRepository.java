package com.zgy.handle.userservice.repository.menu;

import com.zgy.handle.userservice.model.menu.RoleMenu;
import com.zgy.handle.userservice.repository.base.QueryRepository;

import java.util.List;

/**
 * @author a4423
 */
public interface RoleMenuQueryRepository extends QueryRepository<RoleMenu> {
    /**
     * 根据角色ID获取菜单其下的所有菜单
     * @param roleId
     * @return
     */
    List<RoleMenu> findByRoleMenuPK_RoleId(Long roleId);

    /**
     * 根据菜单ID获取其对应的所有按钮的信息
     * @param menuId
     * @return
     */
    List<RoleMenu> findByRoleMenuPK_MenuId(Long menuId);
}
