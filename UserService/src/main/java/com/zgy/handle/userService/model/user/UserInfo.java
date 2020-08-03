package com.zgy.handle.userService.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Data
@Slf4j
@AllArgsConstructor
@Builder
public class UserInfo {
    private String userName;
    private String pasword;
    private String userId;
    private String orgId;
    private String enterpriseId; // 企业id
    private String postId;
    private String postName;
    private Set<String> roles = new HashSet<>();
    public UserInfo(){

    }
}
