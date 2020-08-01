package com.zgy.handle.userService.controller.menu.convert;

import com.zgy.handle.userService.model.dto.menu.MenuQueryDTO;
import com.zgy.handle.userService.model.menu.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuQueryMapper {
    Menu toMenu(MenuQueryDTO menuQueryDTO);

    @Mapping(source = "parent.id", target = "parentId")
    MenuQueryDTO toMenuQueryDto(Menu menu);

    List<MenuQueryDTO> toMenuQueryDtoList(List<Menu> menuList);
}