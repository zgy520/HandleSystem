package com.zgy.handle.userService.controller.user;

import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.model.user.UserInfo;
import com.zgy.handle.userService.service.user.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

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
        log.info("请求的用户名称为:" + loginName);
        Account account = this.accountService.findByLoginName(loginName);
        if (account == null){
            return null;
        }
        UserInfo userInfo = new UserInfo(account.getLoginName(),account.getPassword(),null);
        Set<String> roleSet = new HashSet<>();
        roleSet.add("admin");
        roleSet.add("general");
        userInfo.setRoleSet(roleSet);

        return userInfo;
    }
}
