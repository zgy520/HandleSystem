package com.zgy.handle.userService.repository.user;

import com.zgy.handle.userService.model.user.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findByLoginName(String loginName);
}
