package com.zgy.handle.gateway.config.security;

import com.zgy.handle.gateway.clients.user.UserFeignClient;
import com.zgy.handle.gateway.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserFeignClient userFeignClient;
    private static Map<String,UserInfo> userInfoMap = new HashMap<>();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        if (userInfoMap.containsKey(username)){
            log.info("通过静态变量获取用户名");
            userInfo = userInfoMap.get(username);
        }else {
            log.info("根据用户名进行数据库查询");
            userInfo = userFeignClient.getUserInfo(username);
            userInfoMap.put(username,userInfo);
        }

        if (userInfo != null){

            com.zgy.handle.gateway.model.UserDetails userDetails = new com.zgy.handle.gateway.model.UserDetails(userInfo.getUserName(),
                    userInfo.getPasword(),getGrantedAuthority(userInfo));
            userDetails.setUserId(userInfo.getUserId());
            userDetails.setOrgId(userInfo.getOrgId());
            userDetails.setPostId(userInfo.getPostId());
            userDetails.setPostName(userInfo.getPostName());
            userDetails.setEnterpriseId(userInfo.getEnterpriseId());
            return userDetails;

        }else {
            throw new UsernameNotFoundException("User not found with username : " + username);
        }
    }

    /**
     * 获取授权信息
     * @param userInfo
     * @return
     */
    private List<GrantedAuthority> getGrantedAuthority(UserInfo userInfo){
        Set<String> roleSet = userInfo.getRoles();
        if (roleSet == null) return  null;
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (String role : roleSet){
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role);
            grantedAuthorities.add(simpleGrantedAuthority);
        }
        return grantedAuthorities;
    }
}
