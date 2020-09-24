package com.zgy.handle.userservice.service.menu.update;

import com.zgy.handle.userservice.model.menu.MenuButtonDTO;
import com.zgy.handle.userservice.model.menu.RoleMenuButton;
import com.zgy.handle.userservice.service.base.UpdateService;

import java.util.List;

/**
 * @author: a4423
 * @date: 2020/9/12 20:34
 */
public interface RoleMenuButtonUpdateService extends UpdateService<RoleMenuButton,RoleMenuButton> {
    /**
     * 保存角色、菜单以及按钮的关联关系
     * @param roleId
     * @param menuButtonDTOList
     */
    void updateRoleMenuButton(Long roleId, List<MenuButtonDTO> menuButtonDTOList);
}
