package com.zgy.handle.userservice.model.user.cross;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author a4423
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePostDepartDTO {
    private List<String> roleList;
    private List<String> roleIdList;
    private List<String> postList;
    private List<String> postIdList;
    private String departId;
    private String departName;
}
