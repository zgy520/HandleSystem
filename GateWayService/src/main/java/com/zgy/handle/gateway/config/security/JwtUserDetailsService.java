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
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //log.info(userFeignClient.getUserInfo(username).getUserName());
        UserInfo userInfo = userFeignClient.getUserInfo(username);
        if (userInfo != null){
            Set<String> roleSet = userInfo.getRoleSet();
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (String role : roleSet){
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role);
                grantedAuthorities.add(simpleGrantedAuthority);
            }
            return new User(userInfo.getUserName(),userInfo.getPasword(),grantedAuthorities);
        }else {
            throw new UsernameNotFoundException("User not found with username : " + username);
        }
        /*if ("zgy".equals(username)){
            return new User("zgy","$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                    new ArrayList<>());
        }else {
            throw new UsernameNotFoundException("User not found with username : " + username);
        }*/
    }
}
