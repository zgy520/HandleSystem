package com.zgy.handle.userService.model.user.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountUpdateVo {
    private String id;

    @NotBlank(message = "名称不能为空")
    @Size(min = 2, max = 10, message = "名称必须在2到10个字符长度之间")
    private String name;
    @NotBlank(message = "登录名称不能为空")
    @Size(min = 2, max = 10,message = "登录名称必须在2到10各字符之间")
    private String loginName;

    @Email(message = "请输入正确的有限格式")
    private String email; // email地址
    private String note;
    private List<Long> roleIdList = new ArrayList<>();
    private List<Long> postIdList = new ArrayList<>();
    private Long departId; // 所属部门的id
}