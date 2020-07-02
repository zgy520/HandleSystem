package com.zgy.handle.userService.service.user.update;

import com.zgy.handle.userService.model.structure.Department;
import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.model.user.update.AccountUpdateVo;
import com.zgy.handle.userService.repository.user.update.AccountUpdateRepository;
import com.zgy.handle.userService.service.authority.RoleService;
import com.zgy.handle.userService.service.authority.post.PostService;
import com.zgy.handle.userService.service.base.impl.UpdateServiceImpl;
import com.zgy.handle.userService.service.structure.DepartmentAccountService;
import com.zgy.handle.userService.service.structure.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AccountUpdateServiceImpl extends UpdateServiceImpl<Account, AccountUpdateVo> implements AccountUpdateService {

    @Autowired
    private PostService postService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DepartmentAccountService departmentAccountService;

    private final AccountUpdateRepository accountUpdateRepository;
    @Autowired
    public AccountUpdateServiceImpl(AccountUpdateRepository accountUpdateRepository) {
        super(accountUpdateRepository);
        this.accountUpdateRepository = accountUpdateRepository;
    }

    @Override
    public void beforeUpdate(AccountUpdateVo accountUpdateVo, Account account) {
        if (accountUpdateVo.getPostIdList() != null && accountUpdateVo.getPostIdList().size() > 0){
            account.setPostSet(postService.findByPostIdIn(accountUpdateVo.getPostIdList()));
        }else {
            account.setPostSet(null);
        }
        if (accountUpdateVo.getRoleIdList() != null && accountUpdateVo.getRoleIdList().size() > 0){
            account.setRoleSet(roleService.findByRoleIdIn(accountUpdateVo.getRoleIdList()));
        }else {
            account.setPostSet(null);
        }
        if (StringUtils.isBlank(accountUpdateVo.getId()))
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
