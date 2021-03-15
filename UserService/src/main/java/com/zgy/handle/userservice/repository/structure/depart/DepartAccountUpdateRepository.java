package com.zgy.handle.userservice.repository.structure.depart;

import com.zgy.handle.common.repository.base.UpdateRepository;
import com.zgy.handle.userservice.model.structure.DepartmentAccount;
import org.springframework.stereotype.Repository;

/**
 * @author: a4423
 * @date: 2020/10/5 8:43
 */
@Repository
public interface DepartAccountUpdateRepository extends UpdateRepository<DepartmentAccount> {
    /**
     * 根据用户ID删除对应的用户下的所有关联部门
     * @param accountId
     */
    void deleteByAccountId(Long accountId);
}
