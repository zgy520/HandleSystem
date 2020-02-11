package com.zgy.handle.userService.model.user;

import com.zgy.handle.userService.model.BaseModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.validation.constraints.Email;

@Entity(name = "system_personal")
@Data
@Slf4j
public class Personal extends BaseModel {

    private String code; // 人员编码
    private String name; // 姓名
    private String nickName; // 昵称
    private String phoneNumber; // 邮箱
    @Email
    private String email; // email地址
    private String identity; // 唯一编号
    private int sex; // 性别
    private String address; // 地址
}
