package com.zgy.handle.userService.controller.menu;

import com.zgy.handle.userService.controller.base.UpdateController;
import com.zgy.handle.userService.controller.menu.convert.MenuUpdateMapper;
import com.zgy.handle.userService.model.dto.menu.MenuUpdateDTO;
import com.zgy.handle.userService.model.menu.Menu;
import com.zgy.handle.userService.service.menu.query.MenuQueryService;
import com.zgy.handle.userService.service.menu.update.MenuUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "menu/update")
public class MenuUpdateController extends UpdateController<Menu, MenuUpdateDTO> {
    @Autowired
    private MenuUpdateMapper menuUpdateMapper;
    private MenuQueryService menuQueryService;
    private MenuUpdateService menuUpdateService;

    public MenuUpdateController(MenuUpdateService menuUpdateService, MenuQueryService menuQueryService) {
        super(menuUpdateService, menuQueryService);
    }

    @Override
    public Menu convertUtoT(MenuUpdateDTO menuUpdateDTO) {
        return menuUpdateMapper.toMenu(menuUpdateDTO);
    }
}
