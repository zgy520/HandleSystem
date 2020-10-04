package com.zgy.handle.userservice.service.menu.update;

import com.zgy.handle.common.service.base.UpdateService;
import com.zgy.handle.userservice.model.menu.RoleMenu;

import java.util.List;

/**
 * @author a4423
 */
public interface RoleMenuUpdateService extends UpdateService<RoleMenu, RoleMenu> {
    /**
     * 更新角色和菜单的关系
     *
     * @param roleId
     * @param menuIdList
     */
    void updateRoleMenu(Long roleId, List<Long> menuIdList);
}
