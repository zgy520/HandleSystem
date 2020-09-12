package com.zgy.handle.userservice.model.user.query;

import com.zgy.handle.userservice.model.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountQueryVo extends BaseDTO {
    private String name;
    private String loginName;
    private String email; // email地址
    private String note;
    private List<String> roleList =  new ArrayList<>(); // 角色列表
    private List<String> postList = new ArrayList<>(); // 岗位列表
    private String departName;
    private String roleName;
    private String postName;
    private List<String> roleIdList = new ArrayList<>();
    private List<String> postIdList = new ArrayList<>();
    private String departId; // 所属部门的id
}
