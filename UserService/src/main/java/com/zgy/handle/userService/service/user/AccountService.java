package com.zgy.handle.userService.service.user;

import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.repository.user.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account findByLoginName(String loginName){
        return this.accountRepository.findByLoginName(loginName);
    }
}
