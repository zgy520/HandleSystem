package com.zgy.handle.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Slf4j
@Builder
public class UserInfo{
    private String userName;
    private String pasword;
    private String userId;
    private String orgId;
    private String postId;
    private Set<String> roleSet = new HashSet<>();

}
