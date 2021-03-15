package com.zgy.handle.userservice.model.user;

import com.zgy.handle.userservice.model.authority.role.Role;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;

/**
 * 用户展示信息
 */
@Data
@Slf4j
public class UserDisplayInfo {
    private String name;
    private String introduction;
    private String avatar;
    private List roleID;
    private String roleIDString;
    private Set<Role> roles;
    private List<com.zgy.handle.userservice.dto.RoleMenuBtnDTO> RoleMenuBtnDTO;

}
