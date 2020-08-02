package com.zgy.handle.userService.service.menu.update;

import com.zgy.handle.userService.model.dto.menu.MenuUpdateDTO;
import com.zgy.handle.userService.model.menu.Menu;
import com.zgy.handle.userService.service.base.UpdateService;

public interface MenuUpdateService extends UpdateService<Menu, MenuUpdateDTO> {
    /**
     * 菜单关联按钮
     * @param menuId
     * @param selectedButtonList
     * @return
     */
    String relateButton(Long menuId,String selectedButtonList);
}
