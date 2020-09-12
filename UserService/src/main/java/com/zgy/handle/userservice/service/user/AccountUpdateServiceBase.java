package com.zgy.handle.userservice.service.user;

import com.zgy.handle.userservice.model.structure.Department;
import com.zgy.handle.userservice.model.user.Account;
import com.zgy.handle.userservice.model.user.accountVo.AccountUpdateVo;
import com.zgy.handle.userservice.repository.user.AccountRepository;
import com.zgy.handle.userservice.service.BaseSystemService;
import com.zgy.handle.userservice.service.authority.post.PostServiceBase;
import com.zgy.handle.userservice.service.authority.role.RoleServiceBase;
import com.zgy.handle.userservice.service.structure.DepartmentAccountServiceBase;
import com.zgy.handle.userservice.service.structure.DepartmentServiceBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AccountUpdateServiceBase extends BaseSystemService<Account, AccountUpdateVo> {
    private AccountRepository accountRepository;
    @Autowired
    private DepartmentAccountServiceBase departmentAccountService;
    @Autowired
    private DepartmentServiceBase departmentService;
    @Autowired
    private RoleServiceBase roleService;
    @Autowired
    private PostServiceBase postService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public AccountUpdateServiceBase(AccountRepository accountRepository) {
        super(accountRepository);
        this.accountRepository = accountRepository;
    }

    @Override
    public void beforeUpdate(AccountUpdateVo accountUpdateVo, Account account) {
        if (accountUpdateVo.getPostIdList() != null && accountUpdateVo.getPostIdList().size() > 0){
            account.setPostSet(postService.findByPostIdIn(accountUpdateVo.getPostIdList()));
        }
        if (accountUpdateVo.getRoleIdList() != null && accountUpdateVo.getRoleIdList().size() > 0){
            account.setRoleSet(roleService.findByRoleIdIn(accountUpdateVo.getRoleIdList()));
        }
        account.setPassword(passwordEncoder.encode("123456"));
    }

    @Override
    public void postUpdate(Account account, AccountUpdateVo accountUpdateVo) {
        if (accountUpdateVo.getDepartId() != null){
            Optional<Department> departmentOptional = departmentService.findById(accountUpdateVo.getDepartId());
            if (departmentOptional.isPresent()){
                departmentAccountService.setDepartmentAccount(account,departmentOptional.get());
            }
        }
    }
}
