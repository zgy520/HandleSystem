package com.zgy.handle.userServer.service.sys;

import com.zgy.handle.userServer.data.sys.AccountRepository;
import com.zgy.handle.userServer.model.sys.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Account saveAccount(Account account){
        account.setPassword(passwordEncoder.encode("zgy123"));
        account = accountRepository.save(account);
        return account;
    }
}
