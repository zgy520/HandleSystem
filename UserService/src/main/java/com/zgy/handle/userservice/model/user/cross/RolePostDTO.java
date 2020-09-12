package com.zgy.handle.userservice.model.user.cross;

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
    private List<String> roleIdList = new ArrayList<>();
    private List<String> postList = new ArrayList<>();
    private List<String> postIdList = new ArrayList<>();
    private String departId;
    private String departName;
}
