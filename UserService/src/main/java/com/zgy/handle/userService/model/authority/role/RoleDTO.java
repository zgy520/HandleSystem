package com.zgy.handle.userService.model.authority.role;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RoleDTO {
    private String id;
    private String code;
    private String name;
    private String note;
    //private List<String> userList = new ArrayList<>();
}
