package com.zgy.handle.userservice.controller.role;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userservice.controller.BaseSystemController;
import com.zgy.handle.userservice.controller.role.convert.RoleMapper;
import com.zgy.handle.userservice.model.authority.role.Role;
import com.zgy.handle.userservice.model.authority.role.RoleDTO;
import com.zgy.handle.userservice.model.user.SelectDTO;
import com.zgy.handle.userservice.service.authority.role.RoleServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "role")
@RestController
public class RoleControllerBase extends BaseSystemController<Role, RoleDTO> {
    private RoleServiceBase refactorService;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    public RoleControllerBase(RoleServiceBase refactorService) {
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
            SelectDTO selectDTO = new SelectDTO(role.getId().toString(),role.getName(),role.getId().toString());
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
            SelectDTO selectDTO = new SelectDTO(role.getId().toString(),role.getName(),role.getId().toString());
            selectDTOS.add(selectDTO);
        });
        responseCode.setData(selectDTOS);
        return responseCode;
    }


}
