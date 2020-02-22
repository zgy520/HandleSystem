package com.zgy.handle.userService.controller.role.convert;

import com.zgy.handle.userService.model.authority.Role;
import com.zgy.handle.userService.model.authority.RoleDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDTO toRoleDTO(Role role);
    List<RoleDTO> toRoleDTOs(List<Role> roles);
    Role toRoleDTO(RoleDTO roleDTO);
}
