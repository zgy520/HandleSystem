package com.zgy.handle.userService.controller.user;

import com.zgy.handle.userService.controller.SystemController;
import com.zgy.handle.userService.controller.user.update.AccountUpdateMapper;
import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.model.user.SelectDTO;
import com.zgy.handle.userService.model.user.update.AccountUpdateVo;
import com.zgy.handle.userService.service.user.AccountUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "accountUpdate")
public class AccountUpdateController extends SystemController<Account, AccountUpdateVo> {
    @Autowired
    private AccountUpdateMapper accountUpdateMapper;
    private AccountUpdateService accountUpdateService;
    @Autowired
    public AccountUpdateController(AccountUpdateService accountUpdateService) {
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
