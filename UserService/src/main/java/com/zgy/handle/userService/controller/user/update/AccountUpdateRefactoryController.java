package com.zgy.handle.userService.controller.user.update;

import com.zgy.handle.userService.controller.base.UpdateController;
import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.model.user.update.AccountUpdateVo;
import com.zgy.handle.userService.service.user.query.AccountQueryService;
import com.zgy.handle.userService.service.user.update.AccountUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "account/update")
@Slf4j
public class AccountUpdateRefactoryController extends UpdateController<Account, AccountUpdateVo> {
    @Autowired
    private AccountUpdateMapper accountUpdateMapper;
    private final AccountQueryService accountQueryService;
    private final AccountUpdateService accountUpdateService;
    public AccountUpdateRefactoryController(AccountUpdateService accountUpdateService, AccountQueryService accountQueryService) {
        super(accountUpdateService, accountQueryService);
        this.accountQueryService = accountQueryService;
        this.accountUpdateService = accountUpdateService;

    }

    @Override
    public Account convertUtoT(AccountUpdateVo accountUpdateVo) {
        return accountUpdateMapper.toAccount(accountUpdateVo);
    }
}
