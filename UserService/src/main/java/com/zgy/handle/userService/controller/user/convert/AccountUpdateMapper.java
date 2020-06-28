package com.zgy.handle.userService.controller.user.convert;

import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.model.user.AccountDTO;
import com.zgy.handle.userService.model.user.accountVo.AccountUpdateVo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountUpdateMapper {
    AccountUpdateVo toAccountUpdateVo(Account account);
    List<AccountUpdateVo> toAccountUpdateVos(List<Account> accounts);
    Account toAccount(AccountUpdateVo accountUpdateVo);
}
