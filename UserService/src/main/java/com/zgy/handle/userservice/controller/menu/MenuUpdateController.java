package com.zgy.handle.userservice.controller.menu;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userservice.controller.base.BaseUpdateController;
import com.zgy.handle.userservice.controller.menu.convert.MenuUpdateMapper;
import com.zgy.handle.userservice.model.dto.menu.MenuUpdateDTO;
import com.zgy.handle.userservice.model.menu.Menu;
import com.zgy.handle.userservice.model.menu.MenuButtonDTO;
import com.zgy.handle.userservice.service.menu.query.MenuQueryService;
import com.zgy.handle.userservice.service.menu.update.MenuUpdateService;
import com.zgy.handle.userservice.service.menu.update.RoleMenuButtonUpdateService;
import com.zgy.handle.userservice.service.menu.update.RoleMenuUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping(value = "menu/update")
public class MenuUpdateController extends BaseUpdateController<Menu, MenuUpdateDTO> {
    @Autowired
    private MenuUpdateMapper menuUpdateMapper;
    private MenuQueryService menuQueryService;
    private MenuUpdateService menuUpdateService;
    @Autowired
    private RoleMenuUpdateService roleMenuUpdateService;
    @Autowired
    private RoleMenuButtonUpdateService roleMenuButtonUpdateService;

    public MenuUpdateController(MenuUpdateService menuUpdateService, MenuQueryService menuQueryService) {
        super(menuUpdateService, menuQueryService);
        this.menuQueryService = menuQueryService;
        this.menuUpdateService = menuUpdateService;
    }

    /**
     * 特定菜单关联按钮
     *
     * @param menuId
     * @param selectedButtonList
     * @return
     */
    @PostMapping(value = "relateButton")
    public ResponseCode<String> relateButton(Long menuId, String selectedButtonList) {
        ResponseCode<String> responseCode = ResponseCode.sucess();
        log.info("菜单ID为:" + menuId.toString() + ",选择的用户为:" + selectedButtonList);
        responseCode.setData(menuUpdateService.relateButton(menuId, selectedButtonList));
        return responseCode;
    }

    @PostMapping(value = "updateRoleMenu/{roleId}")
    public ResponseCode<String> updateRoleMenu(@PathVariable("roleId") Long roleId, String menuIdList) {
        ResponseCode<String> responseCode = ResponseCode.sucess();
        roleMenuUpdateService.updateRoleMenu(roleId, Arrays.stream(menuIdList.split(",")).map(Long::parseLong).collect(Collectors.toList()));
        return responseCode;
    }

    @PostMapping(value = "updateRoleMenuBtn/{roleId}")
    public ResponseCode<String> updateRoleMenuButton(@PathVariable("roleId") Long roleId, @RequestBody List<MenuButtonDTO> menuButtonDTOList) {
        ResponseCode<String> responseCode = ResponseCode.sucess();
        roleMenuButtonUpdateService.updateRoleMenuButton(roleId, menuButtonDTOList);
        return responseCode;
    }

    @Override
    public Menu convertUtoT(MenuUpdateDTO menuUpdateDTO) {
        return menuUpdateMapper.toMenu(menuUpdateDTO);
    }
}
