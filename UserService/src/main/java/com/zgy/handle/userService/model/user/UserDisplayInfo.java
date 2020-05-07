package com.zgy.handle.userService.model.user;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户展示信息
 */
@Data
@Slf4j
public class UserDisplayInfo {
    private String name;
    private String introduction;
    private String avatar;
    private Set<String> roleSet = new HashSet<>();
    private Set<String> menuSet = new HashSet<>();
}
