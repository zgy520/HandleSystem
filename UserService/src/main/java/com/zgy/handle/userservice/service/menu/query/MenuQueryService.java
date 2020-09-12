package com.zgy.handle.userservice.service.menu.query;

import com.zgy.handle.userservice.model.common.TransferDTO;
import com.zgy.handle.userservice.model.dto.menu.MenuQueryDTO;
import com.zgy.handle.userservice.model.menu.Menu;
import com.zgy.handle.userservice.service.base.QueryService;

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

    /**
     * 根据菜单ID获取所有的按钮列表，包含选中和未选中的
     * @param menuId
     * @return
     */
    List<TransferDTO> getButtonListByMenuId(Long menuId);

    /**
     * 根据ID列表获取所有的菜单
     * @param menuIdList
     * @return
     */
    List<Menu> findAllByIdIn(List<Long> menuIdList);
}
