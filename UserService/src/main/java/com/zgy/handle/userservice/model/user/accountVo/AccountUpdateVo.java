package com.zgy.handle.userservice.model.user.accountVo;

import com.zgy.handle.userservice.model.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountUpdateVo extends BaseDTO {
    private String name;
    private String loginName;
    @Email
    private String email;
    private String note;
    private Long departId;
    private List<Long> postIdList = new ArrayList<>();
    private List<Long> roleIdList = new ArrayList<>();
}
