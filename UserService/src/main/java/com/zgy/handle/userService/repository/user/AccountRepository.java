package com.zgy.handle.userService.repository.user;

import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.repository.SystemRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends SystemRepository<Account> {
    Account findByLoginName(String loginName);
}
