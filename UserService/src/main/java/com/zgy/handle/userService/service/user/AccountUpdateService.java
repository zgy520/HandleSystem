package com.zgy.handle.userService.service.user;

import com.zgy.handle.userService.model.structure.Department;
import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.model.user.accountVo.AccountUpdateVo;
import com.zgy.handle.userService.repository.user.AccountRepository;
import com.zgy.handle.userService.service.SystemService;
import com.zgy.handle.userService.service.authority.RoleService;
import com.zgy.handle.userService.service.authority.post.PostService;
import com.zgy.handle.userService.service.structure.DepartmentAccountService;
import com.zgy.handle.userService.service.structure.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AccountUpdateService extends SystemService<Account, AccountUpdateVo> {
    private AccountRepository accountRepository;
    @Autowired
    private DepartmentAccountService departmentAccountService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PostService postService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public AccountUpdateService(AccountRepository accountRepository) {
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
