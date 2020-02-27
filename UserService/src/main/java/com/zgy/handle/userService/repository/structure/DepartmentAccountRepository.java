package com.zgy.handle.userService.repository.structure;

import com.zgy.handle.userService.model.structure.DepartmentAccount;
import com.zgy.handle.userService.repository.SystemRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentAccountRepository extends SystemRepository<DepartmentAccount> {
    List<DepartmentAccount> findByAccountId(Long accountId);
}
