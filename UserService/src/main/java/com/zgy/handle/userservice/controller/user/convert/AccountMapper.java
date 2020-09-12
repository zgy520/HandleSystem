package com.zgy.handle.userservice.controller.user.convert;

import com.zgy.handle.userservice.model.user.Account;
import com.zgy.handle.userservice.model.user.AccountDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDTO toAccountDTO(Account account);
    List<AccountDTO> toAccountDTOs(List<Account> accounts);
    Account toAccount(AccountDTO accountDTO);
}
