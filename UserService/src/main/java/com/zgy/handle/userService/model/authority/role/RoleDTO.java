package com.zgy.handle.userService.model.authority.role;

import com.zgy.handle.userService.model.dto.BaseDTO;
import lombok.Data;

@Data
public class RoleDTO extends BaseDTO {
    private String code;
    private String name;
    private String note;
    //private List<String> userList = new ArrayList<>();
}
