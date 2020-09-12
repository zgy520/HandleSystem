package com.zgy.handle.userservice.controller.user.update;

import com.zgy.handle.userservice.model.user.Account;
import com.zgy.handle.userservice.model.user.update.AccountUpdateVo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountUpdateMapper {
    AccountUpdateVo toAccountUpdateVo(Account account);
    List<AccountUpdateVo> toAccountUpdateVos(List<Account> accounts);
    Account toAccount(AccountUpdateVo accountUpdateVo);
}
