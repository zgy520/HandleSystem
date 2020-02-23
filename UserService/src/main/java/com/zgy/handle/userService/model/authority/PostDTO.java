package com.zgy.handle.userService.model.authority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private String id;
    private String name;
    private String code;
    private String note;
    private List<String> userList = new ArrayList<>();
}
