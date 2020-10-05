package com.zgy.handle.userservice.service.structure.depart.query;

import com.zgy.handle.common.service.base.QueryService;
import com.zgy.handle.userservice.model.structure.Department;
import com.zgy.handle.userservice.model.structure.DepartmentAccount;

import java.util.List;

/**
 * @author: a4423
 * @date: 2020/10/5 8:44
 */
public interface DepartAccountQueryService extends QueryService<DepartmentAccount,DepartmentAccount> {
    /**
     * 根据账号ID获取部门
     * @param accountId 账号ID
     * @return 部门信息
     */
    Department getByAccountId(Long accountId);
}
