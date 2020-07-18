package com.zgy.handle.userService.controller.role.query;

import com.zgy.handle.userService.controller.base.QueryController;
import com.zgy.handle.userService.controller.role.convert.RoleMapper;
import com.zgy.handle.userService.model.authority.role.Role;
import com.zgy.handle.userService.model.authority.role.RoleDTO;
import com.zgy.handle.userService.model.user.SelectDTO;
import com.zgy.handle.userService.service.authority.role.query.RoleQueryService;
import com.zgy.handle.userService.service.authority.role.update.RoleUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "role/query")
@Slf4j
public class RoleQueryController extends QueryController<Role, RoleDTO> {

    @Autowired
    private RoleMapper roleMapper;
    private RoleQueryService roleQueryService;
    private RoleUpdateService roleUpdateService;
    public RoleQueryController(RoleUpdateService roleUpdateService, RoleQueryService roleQueryService) {
        super(roleUpdateService, roleQueryService);
        this.roleQueryService = roleQueryService;
        this.roleUpdateService = roleUpdateService;
    }

    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<Role> roles) {
        return null;
    }

    @Override
    public List<RoleDTO> convertTtoU(List<Role> roles) {
        return roleMapper.toRoleDTOs(roles);
    }

    @Override
    public RoleDTO convertTtoU(Role role) {
        return roleMapper.toRoleDTO(role);
    }

    @Override
    public Role convertUtoT(RoleDTO roleDTO) {
        return roleMapper.toRole(roleDTO);
    }
}
