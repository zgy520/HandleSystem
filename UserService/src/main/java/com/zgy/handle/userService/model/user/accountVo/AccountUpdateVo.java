package com.zgy.handle.userService.model.user.accountVo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountUpdateVo {
    private String id;
    private String name;
    private String loginName;
    @Email
    private String email;
    private String note;
    private Long departId;
    private List<Long> postIdList = new ArrayList<>();
    private List<Long> roleIdList = new ArrayList<>();
}
