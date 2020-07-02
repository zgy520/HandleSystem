package com.zgy.handle.userService.controller.user.query;

import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.model.user.query.AccountQueryVo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountQueryMapper {
    AccountQueryVo toAccountQueryVo(Account account);
    Account toAccount(AccountQueryVo accountQueryVo);
    List<AccountQueryVo> toAccountQueryVoList(List<Account> accountList);
    List<Account> toAccountList(List<AccountQueryVo> accountQueryVoList);
}
