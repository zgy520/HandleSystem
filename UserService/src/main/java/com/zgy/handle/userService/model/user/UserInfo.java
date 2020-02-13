package com.zgy.handle.userService.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private Set<String> roleSet = new HashSet<>();
    public UserInfo(){

    }
}
