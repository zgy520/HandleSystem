package com.zgy.handle.userservice.controller.user;

import com.zgy.handle.userservice.controller.BaseSystemController;
import com.zgy.handle.userservice.controller.user.update.AccountUpdateMapper;
import com.zgy.handle.userservice.model.user.Account;
import com.zgy.handle.userservice.model.user.SelectDTO;
import com.zgy.handle.userservice.model.user.update.AccountUpdateVo;
import com.zgy.handle.userservice.service.user.AccountUpdateServiceBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "accountUpdate")
public class AccountUpdateControllerBase extends BaseSystemController<Account, AccountUpdateVo> {
    @Autowired
    private AccountUpdateMapper accountUpdateMapper;
    private AccountUpdateServiceBase accountUpdateService;
    @Autowired
    public AccountUpdateControllerBase(AccountUpdateServiceBase accountUpdateService) {
        super(accountUpdateService);
        this.accountUpdateService = accountUpdateService;
    }

    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<Account> accounts) {
        return null;
    }

    @Override
    public List<AccountUpdateVo> convertTtoU(List<Account> accounts) {
        return accountUpdateMapper.toAccountUpdateVos(accounts);
    }

    @Override
    public AccountUpdateVo convertTtoU(Account account) {
        return accountUpdateMapper.toAccountUpdateVo(account);
    }

    @Override
    public Account convertUtoT(AccountUpdateVo accountUpdateVo) {
        return accountUpdateMapper.toAccount(accountUpdateVo);
    }
}
