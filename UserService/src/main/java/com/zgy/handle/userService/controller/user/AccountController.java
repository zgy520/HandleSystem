package com.zgy.handle.userService.controller.user;

import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.model.user.UserInfo;
import com.zgy.handle.userService.service.user.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户验证的类
 */
@RestController
@Slf4j
@RequestMapping(value = "account")
public class AccountController {
    private AccountService accountService;
    @Autowired
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }
    @PostMapping(value = "findAccountByLoginName")
    public UserInfo findByLoginName(@RequestBody String loginName){
        Account account = this.accountService.findByLoginName(loginName);
        if (account == null){
            return null;
        }
        UserInfo userInfo = UserInfo.builder()
                .userName(account.getLoginName())
                .pasword(account.getPassword())
                .userId(account.getId().toString())
                .orgId("222")
                .postId("333")
                .build();
        Set<String> roleSet = new HashSet<>();
        roleSet.add("admin");
        roleSet.add("general");
        userInfo.setRoleSet(roleSet);

        return userInfo;
    }
}
