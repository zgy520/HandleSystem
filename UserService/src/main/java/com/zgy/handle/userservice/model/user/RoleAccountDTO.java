package com.zgy.handle.userservice.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: a4423
 * @date: 2020/10/5 18:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleAccountDTO {
    private String accountName;
    private String loginName;
    private String roleName;
    private String roleCode;
}
