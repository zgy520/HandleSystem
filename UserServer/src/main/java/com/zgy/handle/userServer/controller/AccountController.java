package com.zgy.handle.userServer.controller;

import com.zgy.handle.userServer.model.ResponseCode;
import com.zgy.handle.userServer.model.sys.Account;
import com.zgy.handle.userServer.service.sys.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping(value = "update")
    public ResponseCode<Account> update(Long id,Account account){
        ResponseCode responseCode = ResponseCode.sucess();
        Account responseAccount = accountService.saveAccount(account);
        responseCode.setData(responseAccount);
        return responseCode;
    }
}
