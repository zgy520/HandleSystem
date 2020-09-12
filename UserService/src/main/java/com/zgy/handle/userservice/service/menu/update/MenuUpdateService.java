package com.zgy.handle.userservice.service.menu.update;

import com.zgy.handle.userservice.model.dto.menu.MenuUpdateDTO;
import com.zgy.handle.userservice.model.menu.Menu;
import com.zgy.handle.userservice.service.base.UpdateService;

public interface MenuUpdateService extends UpdateService<Menu, MenuUpdateDTO> {
    /**
     * 菜单关联按钮
     * @param menuId
     * @param selectedButtonList
     * @return
     */
    String relateButton(Long menuId,String selectedButtonList);
}
