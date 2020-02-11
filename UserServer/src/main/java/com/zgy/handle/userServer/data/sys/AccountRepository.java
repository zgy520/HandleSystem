package com.zgy.handle.userServer.data.sys;

import com.zgy.handle.userServer.model.sys.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findByLoginName(String loginName);
}
