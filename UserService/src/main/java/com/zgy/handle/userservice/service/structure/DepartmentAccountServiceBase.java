package com.zgy.handle.userservice.service.structure;

import com.zgy.handle.userservice.model.structure.DepartPersonalType;
import com.zgy.handle.userservice.model.structure.Department;
import com.zgy.handle.userservice.model.structure.DepartmentAccount;
import com.zgy.handle.userservice.model.structure.DepartmentAccountPK;
import com.zgy.handle.userservice.model.user.Account;
import com.zgy.handle.userservice.repository.structure.DepartmentAccountRepository;
import com.zgy.handle.userservice.service.BaseSystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DepartmentAccountServiceBase extends BaseSystemService<DepartmentAccount,DepartmentAccount> {
    private DepartmentAccountRepository departmentAccountRepository;
    public DepartmentAccountServiceBase(DepartmentAccountRepository departmentAccountRepository) {
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
        departmentAccountRepository.deleteByAccountId(account.getId()); // 删除已有数据
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

    public List<Account> getAccountListByDepartmentId(Long departId){
        List<DepartmentAccount> departmentAccountList = departmentAccountRepository.findByDepartmentId(departId);
        if (departmentAccountList != null && departmentAccountList.size() > 0){
            return departmentAccountList.stream().map(DepartmentAccount::getAccount).collect(Collectors.toList());
        }
        return null;
    }

    public int deleteByAccountId(Long accountId){
        return departmentAccountRepository.deleteByAccountId(accountId);
    }

    public void relateAccountsByDepartmentId(Department department, Set<Account> accountSet){
        this.deleteByDepartId(department.getId());
        accountSet.stream().forEach(account -> {
            setDepartmentAccount(account,department);
        });
    }

    public void deleteByDepartId(Long departId){
        departmentAccountRepository.deleteByDepartmentId(departId);
    }
}
