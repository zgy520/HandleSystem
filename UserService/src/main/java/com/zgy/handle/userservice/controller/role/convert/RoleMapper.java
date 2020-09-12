package com.zgy.handle.userservice.controller.role.convert;

import com.zgy.handle.userservice.model.authority.role.Role;
import com.zgy.handle.userservice.model.authority.role.RoleDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDTO toRoleDTO(Role role);
    List<RoleDTO> toRoleDTOs(List<Role> roles);
    Role toRole(RoleDTO roleDTO);
}
