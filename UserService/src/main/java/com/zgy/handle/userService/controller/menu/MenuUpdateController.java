package com.zgy.handle.userService.controller.menu;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userService.controller.base.UpdateController;
import com.zgy.handle.userService.controller.menu.convert.MenuUpdateMapper;
import com.zgy.handle.userService.model.dto.menu.MenuUpdateDTO;
import com.zgy.handle.userService.model.menu.Menu;
import com.zgy.handle.userService.service.menu.query.MenuQueryService;
import com.zgy.handle.userService.service.menu.update.MenuUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
        this.menuQueryService = menuQueryService;
        this.menuUpdateService = menuUpdateService;
    }

    /**
     * 特定菜单关联按钮
     * @param menuId
     * @param selectedButtonList
     * @return
     */
    @PostMapping(value = "relateButton")
    public ResponseCode<String> relateButton(Long menuId,String selectedButtonList){
        ResponseCode<String> responseCode = ResponseCode.sucess();
        log.info("菜单ID为:" + menuId.toString() + ",选择的用户为:" + selectedButtonList);
        responseCode.setData(menuUpdateService.relateButton(menuId,selectedButtonList));
        return responseCode;
    }

    @Override
    public Menu convertUtoT(MenuUpdateDTO menuUpdateDTO) {
        return menuUpdateMapper.toMenu(menuUpdateDTO);
    }
}
