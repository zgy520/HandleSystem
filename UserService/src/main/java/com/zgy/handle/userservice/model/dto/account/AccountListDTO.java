package com.zgy.handle.userservice.model.dto.account;

import com.zgy.handle.userservice.model.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: a4423
 * @date: 2021/3/22 6:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountListDTO extends BaseDTO {
    private String name;
    private String email;
    private String loginName;
    private String note;
    private String departId;
    private String departName;
}
