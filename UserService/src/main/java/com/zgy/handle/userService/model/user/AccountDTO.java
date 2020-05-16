package com.zgy.handle.userService.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private String id;
    private String name;
    private String loginName;
    private String phoneNumber; // 手机号
    private String handleUser;
    private String password;
    @Email
    private String email; // email地址
    private String note;
    /*private List<String> roleList =  new ArrayList<>(); // 角色列表
    private List<String> roleIdList = new ArrayList<>(); // 角色id列表*/
    private List<String> postList = new ArrayList<>(); // 岗位列表
    private String departId; // 所属部门的id
    private String departName;
    private String roleId;
    private String roleName;
    private AccountType accountType; // 用户类型
}
