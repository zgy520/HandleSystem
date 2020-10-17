package com.zgy.handle.userservice.controller.role.update;

import com.zgy.excel.export.ExportTools;
import com.zgy.handle.common.controller.base.BaseUpdateController;
import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userservice.controller.role.convert.RoleMapper;
import com.zgy.handle.userservice.model.authority.role.Role;
import com.zgy.handle.userservice.model.authority.role.RoleDTO;
import com.zgy.handle.userservice.service.authority.role.excel.RoleImportService;
import com.zgy.handle.userservice.service.authority.role.query.RoleQueryService;
import com.zgy.handle.userservice.service.authority.role.update.RoleUpdateService;
import com.zgy.handle.userservice.service.excel.BusinessType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author a4423
 */
@RestController
@RequestMapping(value = "role/update")
@Slf4j
public class RoleUpdateController extends BaseUpdateController<Role, RoleDTO> {

    @Autowired
    private RoleMapper roleMapper;
    private RoleQueryService roleQueryService;
    private RoleUpdateService roleUpdateService;
    @Autowired
    private RoleImportService roleImportService;

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
     *
     * @param selectedUserList
     * @return
     */
    @PostMapping(value = "relateUser")
    public ResponseCode<String> relateUser(Long roleId, String selectedUserList) {
        ResponseCode<String> responseCode = ResponseCode.sucess();
        log.info("角色ID为:" + roleId.toString() + ",选择的用户为:" + selectedUserList);
        responseCode.setData(roleUpdateService.relateUser(roleId, selectedUserList));
        return responseCode;
    }

    @PostMapping(value = "importRole")
    public ResponseCode<String> importRole(@RequestParam(value = "file") MultipartFile file,String attachData) {
        log.info("attachData: " + attachData);
        return roleImportService.importExcel(file, attachData);
    }

}
