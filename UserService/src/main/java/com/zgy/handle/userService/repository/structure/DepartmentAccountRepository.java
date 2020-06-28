package com.zgy.handle.userService.repository.structure;

import com.zgy.handle.userService.model.structure.DepartmentAccount;
import com.zgy.handle.userService.repository.SystemRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DepartmentAccountRepository extends SystemRepository<DepartmentAccount> {
    List<DepartmentAccount> findByAccountId(Long accountId);
    @Transactional
    @Modifying
    int deleteByAccountId(Long accountId);


}
