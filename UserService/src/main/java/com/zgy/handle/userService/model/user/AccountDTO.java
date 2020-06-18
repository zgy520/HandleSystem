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
    //private String code; // 人员编码
    //private String phoneNumber; // 手机号
    @Email(message = "请输入正确的有限格式")
    private String email; // email地址
    private String note;
    //private String identity; // 唯一编号
    //private int sex; // 性别
    //private String address; // 地址
    private List<String> roleList =  new ArrayList<>(); // 角色列表
    private List<String> postList = new ArrayList<>(); // 岗位列表
    private String departId; // 所属部门的id
    private String departName;
}
