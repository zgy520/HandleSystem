package com.zgy.handle.userService.service.structure;

import com.zgy.handle.userService.model.structure.DepartPersonalType;
import com.zgy.handle.userService.model.structure.Department;
import com.zgy.handle.userService.model.structure.DepartmentAccount;
import com.zgy.handle.userService.model.structure.DepartmentAccountPK;
import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.repository.structure.DepartmentAccountRepository;
import com.zgy.handle.userService.service.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DepartmentAccountService extends SystemService<DepartmentAccount> {
    private DepartmentAccountRepository departmentAccountRepository;
    public DepartmentAccountService(DepartmentAccountRepository departmentAccountRepository) {
        super(departmentAccountRepository);
        this.departmentAccountRepository = departmentAccountRepository;
    }

    public void setDepartmentAccount(Account account, Department department){
        DepartmentAccount departmentAccount = DepartmentAccount.builder()
                .account(account)
                .department(department)
                .personalType(DepartPersonalType.MEMBER)
                .sortOrder(0)
                .build();
        DepartmentAccountPK departmentAccountPK = new DepartmentAccountPK();
        departmentAccountPK.setAccountId(account.getId());
        departmentAccountPK.setDepartId(department.getId());
        departmentAccount.setId(departmentAccountPK);
        departmentAccountRepository.save(departmentAccount);
    }

    /**
     * 根据人员id获取部门
     * @param accountId
     * @return
     */
    public Department getByAccountId(Long accountId){
        List<DepartmentAccount> departmentAccountList = departmentAccountRepository.findByAccountId(accountId);
        if (departmentAccountList != null && departmentAccountList.size() > 0){
            return departmentAccountList.get(0).getDepartment();
        }
        return null;
    }

}
