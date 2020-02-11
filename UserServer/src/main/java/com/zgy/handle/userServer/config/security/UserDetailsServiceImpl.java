package com.zgy.handle.userServer.config.security;

import com.zgy.handle.userServer.data.sys.AccountRepository;
import com.zgy.handle.userServer.model.sys.Account;
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

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        Account account = accountRepository.findByLoginName(loginName);
        if (account == null){
            throw new UsernameNotFoundException("User not found with loginNmae: " + loginName);
        }
        List<GrantedAuthority> roleNameList = new ArrayList<>();
        roleNameList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        roleNameList.add(new SimpleGrantedAuthority("ROLE_AQY"));
        UserDetails userDetails = new User(account.getLoginName(),account.getPassword(),roleNameList);
        return userDetails;
    }
}
