package com.zgy.handle.userservice.service.structure.depart.query;

import com.zgy.handle.common.repository.base.QueryRepository;
import com.zgy.handle.common.service.base.impl.BaseQueryServiceImpl;
import com.zgy.handle.userservice.model.structure.Department;
import com.zgy.handle.userservice.model.structure.DepartmentAccount;
import com.zgy.handle.userservice.repository.structure.depart.DepartAccountQueryRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: a4423
 * @date: 2020/10/5 8:44
 */
@Service
public class DepartAccountQueryServiceImpl extends BaseQueryServiceImpl<DepartmentAccount,DepartmentAccount> implements DepartAccountQueryService {
    private DepartAccountQueryRepository departAccountQueryRepository;
    public DepartAccountQueryServiceImpl(DepartAccountQueryRepository departAccountQueryRepository) {
        super(departAccountQueryRepository);
        this.departAccountQueryRepository = departAccountQueryRepository;
    }

    @Override
    public Specification<DepartmentAccount> querySpecification(DepartmentAccount dto) {
        return null;
    }

    @Override
    public Department getByAccountId(Long accountId) {
        List<DepartmentAccount> departmentAccounts = departAccountQueryRepository.findByAccountId(accountId);
        if (departmentAccounts != null && departmentAccounts.size() > 0){
            return departmentAccounts.get(0).getDepartment();
        }
        return null;
    }
}
