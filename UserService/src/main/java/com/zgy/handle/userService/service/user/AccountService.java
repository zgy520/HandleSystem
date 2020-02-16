package com.zgy.handle.userService.service.user;

import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.repository.user.AccountRepository;
import com.zgy.handle.userService.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumSet;

@Service
public class AccountService extends SystemService<Account> {
    private AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository){
        super(accountRepository);
        this.accountRepository = accountRepository;
    }

    public Account findByLoginName(String loginName){
        return this.accountRepository.findByLoginName(loginName);
    }

    public void test(){

    }
}
