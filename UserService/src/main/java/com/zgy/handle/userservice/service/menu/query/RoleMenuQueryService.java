package com.zgy.handle.userservice.service.menu.query;

import com.zgy.handle.common.service.base.QueryService;
import com.zgy.handle.userservice.model.menu.RoleMenu;

import java.util.List;

/**
 * @author a4423
 */
public interface RoleMenuQueryService extends QueryService<RoleMenu, RoleMenu> {
    /**
     * 根据角色ID获取所有的菜单ID列表
     *
     * @param roleId
     * @return
     */
    List<Long> getMenuIdListByRoleId(Long roleId);

    /**
     * 根据菜单ID获取所有的角色ID列表
     *
     * @param menuId
     * @return
     */
    List<Long> getRoleIdListByMenuId(Long menuId);

}
