package com.zgy.handle.userService.controller.role.convert;

import com.zgy.handle.userService.model.authority.Role;
import com.zgy.handle.userService.model.authority.RoleDTO;
import com.zgy.handle.userService.service.authority.RoleService;
import com.zgy.handle.userService.service.user.AccountService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDTO toRoleDTO(Role role);
    List<RoleDTO> toRoleDTOs(List<Role> roles);
    Role toRole(RoleDTO roleDTO);
}
