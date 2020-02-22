package com.zgy.handle.userService.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private String id;
    private String name;
    private String loginName;
    //private String code; // 人员编码
    //private String phoneNumber; // 手机号
    private String email; // email地址
    //private String identity; // 唯一编号
    //private int sex; // 性别
    //private String address; // 地址
}
