package com.zgy.handle.userservice.repository.structure;

import com.zgy.handle.userservice.model.structure.DepartmentAccount;
import com.zgy.handle.userservice.repository.SystemRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DepartmentAccountRepository extends SystemRepository<DepartmentAccount> {
    List<DepartmentAccount> findByAccountId(Long accountId);
    List<DepartmentAccount> findByDepartmentId(Long departmentId);
    @Transactional
    @Modifying
    int deleteByAccountId(Long accountId);

    @Transactional
    @Modifying
    int deleteByDepartmentId(Long departmentId);


}
