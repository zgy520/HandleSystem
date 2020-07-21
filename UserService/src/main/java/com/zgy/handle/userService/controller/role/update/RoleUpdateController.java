package com.zgy.handle.userService.controller.role.update;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userService.controller.base.UpdateController;
import com.zgy.handle.userService.controller.role.convert.RoleMapper;
import com.zgy.handle.userService.model.authority.role.Role;
import com.zgy.handle.userService.model.authority.role.RoleDTO;
import com.zgy.handle.userService.service.authority.role.query.RoleQueryService;
import com.zgy.handle.userService.service.authority.role.update.RoleUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "role/update")
@Slf4j
public class RoleUpdateController extends UpdateController<Role, RoleDTO> {

    @Autowired
    private RoleMapper roleMapper;
    private RoleQueryService roleQueryService;
    private RoleUpdateService roleUpdateService;
    public RoleUpdateController(RoleUpdateService roleUpdateService, RoleQueryService roleQueryService) {
        super(roleUpdateService, roleQueryService);
        this.roleQueryService = roleQueryService;
        this.roleUpdateService = roleUpdateService;
    }

    @Override
    public Role convertUtoT(RoleDTO roleDTO) {
        return roleMapper.toRole(roleDTO);
    }

    /**
     * 角色关联用户
     * @param selectedUserList
     * @return
     */
    @PostMapping(value = "relateUser")
    public ResponseCode<String> relateUser(Long roleId,String selectedUserList){
        ResponseCode<String> responseCode = ResponseCode.sucess();
        log.info("角色ID为:" + roleId.toString() + ",选择的用户为:" + selectedUserList);
        responseCode.setData(roleUpdateService.relateUser(roleId,selectedUserList));
        return responseCode;
    }
}
