package com.zgy.handle.userService.controller.role;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userService.controller.SystemController;
import com.zgy.handle.userService.controller.role.convert.RoleMapper;
import com.zgy.handle.userService.model.authority.Role;
import com.zgy.handle.userService.model.authority.RoleDTO;
import com.zgy.handle.userService.model.user.SelectDTO;
import com.zgy.handle.userService.service.authority.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "role")
@RestController
public class RoleController extends SystemController<Role, RoleDTO> {
    private RoleService refactorService;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    public RoleController(RoleService refactorService) {
        super(refactorService);
        this.refactorService = refactorService;
    }

    @Override
    public void fillList(List<Role> entityList, List<RoleDTO> dtoList) {
        refactorService.fetchAccountByRole(dtoList);
    }

    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<Role> roleList) {
        List<SelectDTO> selectDTOList = new ArrayList<>();
        roleList.stream().forEach(role -> {
            SelectDTO selectDTO = new SelectDTO(role.getId().toString(),role.getName());
            selectDTOList.add(selectDTO);
        });
        return selectDTOList;
    }

    @Override
    public List<RoleDTO> convertTtoU(List<Role> roleList) {
        return roleMapper.toRoleDTOs(roleList);
    }

    @Override
    public RoleDTO convertTtoU(Role role) {
        return roleMapper.toRoleDTO(role);
    }

    @Override
    public Role convertUtoT(RoleDTO roleDTO) {
        return roleMapper.toRole(roleDTO);
    }

    @GetMapping(value = "getRoleList")
    public ResponseCode<List<SelectDTO>> getRoleList(){
        ResponseCode<List<SelectDTO>> responseCode = ResponseCode.sucess();
        List<Role> roleList = refactorService.findAll();
        List<SelectDTO> selectDTOS = new ArrayList<>();
        roleList.stream().forEach(role -> {
            SelectDTO selectDTO = new SelectDTO(role.getId().toString(),role.getName());
            selectDTOS.add(selectDTO);
        });
        responseCode.setData(selectDTOS);
        return responseCode;
    }
}
