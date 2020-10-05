package com.zgy.handle.userservice.controller.role.query;

import com.zgy.handle.common.controller.base.BaseQueryController;
import com.zgy.handle.common.model.common.SelectDTO;
import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userservice.controller.role.convert.RoleMapper;
import com.zgy.handle.userservice.model.authority.role.Role;
import com.zgy.handle.userservice.model.authority.role.RoleDTO;
import com.zgy.handle.userservice.model.common.TransferDTO;
import com.zgy.handle.userservice.service.authority.role.query.RoleQueryService;
import com.zgy.handle.userservice.service.authority.role.update.RoleUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "role/query")
@Slf4j
public class RoleQueryController extends BaseQueryController<Role, RoleDTO> {

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
        List<SelectDTO> selectDTOList = new ArrayList<>();
        roles.stream().forEach(role -> {
            SelectDTO selectDTO = new SelectDTO(role.getId().toString(),role.getName(),role.getId().toString());
            selectDTOList.add(selectDTO);
        });
        return selectDTOList;
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

    @GetMapping(value = "getAccountListByRoleId")
    public ResponseCode<List<TransferDTO>> getAccountListByRoleId(Long roleId) {
        ResponseCode<List<TransferDTO>> responseCode = ResponseCode.sucess();
        /*Set<Account> accountSet = roleQueryService.getAccountListByRoleId(roleId);
        accountSet.stream().forEach(account -> {
            SelectDTO selectDTO = new SelectDTO(account.getId().toString(),account.getName(),account.getId().toString());
            selectDTOList.add(selectDTO);
        });*/
        responseCode.setData(roleQueryService.getAccountListByRoleId(roleId));
        return responseCode;
    }


}
