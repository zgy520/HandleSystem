package com.zgy.handle.userService.controller.menu;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userService.controller.base.QueryController;
import com.zgy.handle.userService.controller.menu.convert.MenuQueryMapper;
import com.zgy.handle.userService.model.common.TransferDTO;
import com.zgy.handle.userService.model.dto.menu.MenuQueryDTO;
import com.zgy.handle.userService.model.menu.Menu;
import com.zgy.handle.userService.model.user.SelectDTO;
import com.zgy.handle.userService.service.menu.query.MenuQueryService;
import com.zgy.handle.userService.service.menu.update.MenuUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
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
