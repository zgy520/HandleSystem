package com.zgy.handle.userService.model.user.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountUpdateVo {
    private String id;

    private String name;
    private String loginName;

    @Email(message = "请输入正确的有限格式")
    private String email; // email地址
    private String note;
    private List<Long> roleIdList = new ArrayList<>();
    private List<Long> postIdList = new ArrayList<>();
    private Long departId; // 所属部门的id
}
