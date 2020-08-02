package com.zgy.handle.userService.controller.user.convert;

import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.model.user.AccountDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-01T22:39:34+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.4 (Oracle Corporation)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public AccountDTO toAccountDTO(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountDTO accountDTO = new AccountDTO();

        if ( account.getId() != null ) {
            accountDTO.setId( String.valueOf( account.getId() ) );
        }
        accountDTO.setName( account.getName() );
        accountDTO.setLoginName( account.getLoginName() );
        accountDTO.setEmail( account.getEmail() );
        accountDTO.setNote( account.getNote() );

        return accountDTO;
    }

    @Override
    public List<AccountDTO> toAccountDTOs(List<Account> accounts) {
        if ( accounts == null ) {
            return null;
        }

        List<AccountDTO> list = new ArrayList<AccountDTO>( accounts.size() );
        for ( Account account : accounts ) {
            list.add( toAccountDTO( account ) );
        }

        return list;
    }

    @Override
    public Account toAccount(AccountDTO accountDTO) {
        if ( accountDTO == null ) {
            return null;
        }

        Account account = new Account();

        if ( accountDTO.getId() != null ) {
            account.setId( Long.parseLong( accountDTO.getId() ) );
        }
        account.setNote( accountDTO.getNote() );
        account.setName( accountDTO.getName() );
        account.setLoginName( accountDTO.getLoginName() );
        account.setEmail( accountDTO.getEmail() );

        return account;
    }
}
