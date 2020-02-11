package com.zgy.handle.userServer.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户信息
 */
@Data
@Slf4j
public class UserInfo {
    private String name;
    private String introduction;
    private String avatar;
    private Set<String> roleSet = new HashSet<>();
}
