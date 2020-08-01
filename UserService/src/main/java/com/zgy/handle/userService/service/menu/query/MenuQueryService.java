package com.zgy.handle.userService.service.menu.query;

import com.zgy.handle.userService.model.dto.menu.MenuQueryDTO;
import com.zgy.handle.userService.model.menu.Menu;
import com.zgy.handle.userService.service.base.QueryService;

import java.util.List;

public interface MenuQueryService extends QueryService<Menu, MenuQueryDTO> {
    /**
     * 动态查询菜单
     *
     * @param menuQueryDTO
     * @return
     */
    List<Menu> findBySpecification(MenuQueryDTO menuQueryDTO);

    /**
     * 获取菜单得树形结构
     *
     * @param menuQueryDTOList
     * @return
     */
    List<MenuQueryDTO> getTreeMenuQueryDto(List<MenuQueryDTO> menuQueryDTOList);
}
