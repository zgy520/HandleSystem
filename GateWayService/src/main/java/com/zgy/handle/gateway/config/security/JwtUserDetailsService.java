package com.zgy.handle.gateway.config.security;

import com.zgy.handle.gateway.clients.user.UserFeignClient;
import com.zgy.handle.gateway.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserFeignClient userFeignClient;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //log.info(userFeignClient.getUserInfo(username).getUserName());
        UserInfo userInfo = userFeignClient.getUserInfo(username);
        if (userInfo != null){

            //return new User(userInfo.getUserName(),userInfo.getPasword(),getGrantedAuthority(userInfo));
            com.zgy.handle.gateway.model.UserDetails userDetails = new com.zgy.handle.gateway.model.UserDetails(userInfo.getUserName(),
                    userInfo.getPasword(),getGrantedAuthority(userInfo));
            userDetails.setUserId(userInfo.getUserId());
            userDetails.setOrgId(userInfo.getOrgId());
            userDetails.setPostId(userInfo.getPostId());
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
        Set<String> roleSet = userInfo.getRoleSet();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (String role : roleSet){
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role);
            grantedAuthorities.add(simpleGrantedAuthority);
        }
        return grantedAuthorities;
    }
}
