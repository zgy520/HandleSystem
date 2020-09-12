package com.zgy.handle.userservice.controller.menu;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userservice.controller.base.BaseQueryController;
import com.zgy.handle.userservice.controller.menu.convert.MenuQueryMapper;
import com.zgy.handle.userservice.model.common.TransferDTO;
import com.zgy.handle.userservice.model.dto.menu.MenuQueryDTO;
import com.zgy.handle.userservice.model.menu.Menu;
import com.zgy.handle.userservice.model.user.SelectDTO;
import com.zgy.handle.userservice.service.menu.query.MenuQueryService;
import com.zgy.handle.userservice.service.menu.query.RoleMenuQueryService;
import com.zgy.handle.userservice.service.menu.update.MenuUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "menu/query")
public class MenuQueryController extends BaseQueryController<Menu, MenuQueryDTO> {
    @Autowired
    private MenuQueryMapper menuQueryMapper;
    private MenuQueryService menuQueryService;
    private MenuUpdateService menuUpdateService;
    @Autowired
    private RoleMenuQueryService roleMenuQueryService;

    public MenuQueryController(MenuUpdateService menuUpdateService, MenuQueryService menuQueryService) {
        super(menuUpdateService, menuQueryService);
        this.menuQueryService = menuQueryService;
        this.menuUpdateService = menuUpdateService;
    }

    @Override
    public ResponseCode<List<MenuQueryDTO>> list(Pageable pageable, MenuQueryDTO dto) {
        ResponseCode<List<MenuQueryDTO>> responseCode = ResponseCode.sucess();
        List<Menu> menuList = menuQueryService.findBySpecification(dto);
        List<MenuQueryDTO> industryQueryDTOList = menuQueryMapper.toMenuQueryDtoList(menuList);
        responseCode.setData(menuQueryService.getTreeMenuQueryDto(industryQueryDTOList));
        return responseCode;
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

    @GetMapping(value = "getButtonListByMenuId")
    public ResponseCode<List<TransferDTO>> getButtonListByMenuId(Long menuId){
        ResponseCode<List<TransferDTO>> responseCode = ResponseCode.sucess();
        responseCode.setData(menuQueryService.getButtonListByMenuId(menuId));
        return responseCode;
    }

    @GetMapping(value = "getMenuListByRoleId/{roleId}")
    public ResponseCode<List<MenuQueryDTO>> getMenuListByRoleId(@PathVariable Long roleId){
        ResponseCode<List<MenuQueryDTO>> responseCode = ResponseCode.sucess();
        responseCode.setData(menuQueryService.getTreeMenuQueryDto(menuQueryMapper.toMenuQueryDtoList(menuQueryService.findAllByIdIn(roleMenuQueryService.getMenuIdListByRoleId(roleId)))));
        return responseCode;
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
