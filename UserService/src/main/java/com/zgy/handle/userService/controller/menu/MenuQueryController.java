package com.zgy.handle.userService.controller.menu;

import com.zgy.handle.userService.controller.base.QueryController;
import com.zgy.handle.userService.controller.menu.convert.MenuQueryMapper;
import com.zgy.handle.userService.model.dto.menu.MenuQueryDTO;
import com.zgy.handle.userService.model.menu.Menu;
import com.zgy.handle.userService.model.user.SelectDTO;
import com.zgy.handle.userService.service.menu.query.MenuQueryService;
import com.zgy.handle.userService.service.menu.update.MenuUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "menu/query")
public class MenuQueryController extends QueryController<Menu, MenuQueryDTO> {
    @Autowired
    private MenuQueryMapper menuQueryMapper;
    private MenuQueryService menuQueryService;
    private MenuUpdateService menuUpdateService;

    public MenuQueryController(MenuUpdateService menuUpdateService, MenuQueryService menuQueryService) {
        super(menuUpdateService, menuQueryService);
    }

    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<Menu> menus) {
        List<SelectDTO> selectDTOList = new ArrayList<>();
        menus.stream().forEach(menu -> {
            SelectDTO selectDTO = new SelectDTO(menu.getId().toString(), menu.getName(), menu.getId().toString());
            selectDTOList.add(selectDTO);
        });
        return selectDTOList;
    }

    @Override
    public List<MenuQueryDTO> convertTtoU(List<Menu> menus) {
        return menuQueryMapper.toMenuQueryDtoList(menus);
    }

    @Override
    public MenuQueryDTO convertTtoU(Menu menu) {
        return menuQueryMapper.toMenuQueryDto(menu);
    }

    @Override
    public Menu convertUtoT(MenuQueryDTO menuQueryDTO) {
        return menuQueryMapper.toMenu(menuQueryDTO);
    }
}
