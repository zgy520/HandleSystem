package com.zgy.handle.userservice.repository.structure.depart;

import com.zgy.handle.common.repository.base.QueryRepository;
import com.zgy.handle.userservice.model.structure.DepartmentAccount;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: a4423
 * @date: 2020/10/5 8:42
 */
@Repository
public interface DepartAccountQueryRepository extends QueryRepository<DepartmentAccount> {
    /**
     * 根据账号ID获取部门信息
     * @param accountId
     * @return
     */
    List<DepartmentAccount> findByAccountId(Long accountId);
}
