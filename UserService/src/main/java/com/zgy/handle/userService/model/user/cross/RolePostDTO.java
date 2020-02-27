package com.zgy.handle.userService.model.user.cross;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePostDTO {
    private List<String> roleList = new ArrayList<>();
    private List<String> postList = new ArrayList<>();
    private String departId;
}
