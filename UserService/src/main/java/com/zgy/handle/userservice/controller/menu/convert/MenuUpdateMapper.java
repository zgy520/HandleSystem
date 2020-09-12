package com.zgy.handle.userservice.controller.menu.convert;

import com.zgy.handle.userservice.model.dto.menu.MenuUpdateDTO;
import com.zgy.handle.userservice.model.menu.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuUpdateMapper {
    Menu toMenu(MenuUpdateDTO menuUpdateDTO);

    @Mapping(source = "parent.id", target = "parentId")
    MenuUpdateDTO toMenuUpdateDto(Menu menu);

    List<MenuUpdateDTO> toMeuUpdateDtoList(List<Menu> menuList);
}
