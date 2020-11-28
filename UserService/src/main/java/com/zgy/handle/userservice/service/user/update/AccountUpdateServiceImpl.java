package com.zgy.handle.userservice.service.user.update;

import com.zgy.handle.common.model.common.UniqueInfo;
import com.zgy.handle.common.service.base.impl.BaseUpdateServiceImpl;
import com.zgy.handle.userservice.core.error.ErrorNum;
import com.zgy.handle.userservice.core.exception.BusinessException;
import com.zgy.handle.userservice.model.structure.DepartPersonalType;
import com.zgy.handle.userservice.model.structure.Department;
import com.zgy.handle.userservice.model.structure.DepartmentAccount;
import com.zgy.handle.userservice.model.structure.DepartmentAccountPK;
import com.zgy.handle.userservice.model.user.Account;
import com.zgy.handle.userservice.model.user.update.AccountUpdateVo;
import com.zgy.handle.userservice.repository.structure.depart.DepartAccountUpdateRepository;
import com.zgy.handle.userservice.repository.structure.depart.DepartQueryRepository;
import com.zgy.handle.userservice.repository.user.query.AccountQueryRepository;
import com.zgy.handle.userservice.repository.user.update.AccountUpdateRepository;
import com.zgy.handle.userservice.service.structure.depart.query.DepartQueryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@Slf4j
public class AccountUpdateServiceImpl extends BaseUpdateServiceImpl<Account, AccountUpdateVo> implements AccountUpdateService {

    /* @Autowired
     private PostServiceBase postService;
     @Autowired
     private RoleServiceBase roleService;*/
    @Autowired
    private PasswordEncoder passwordEncoder;
    /*@Autowired
    private DepartmentServiceBase departmentService;
    @Autowired
    private DepartmentAccountServiceBase departmentAccountService;*/
    @Autowired
    private AccountQueryRepository accountQueryRepository;
    @Autowired
    private DepartQueryService departQueryService;
    @Autowired
    private DepartQueryRepository departQueryRepository;
    @Autowired
    private DepartAccountUpdateRepository departAccountUpdateRepository;

    private final AccountUpdateRepository accountUpdateRepository;

    @Autowired
    public AccountUpdateServiceImpl(AccountUpdateRepository accountUpdateRepository) {
        super(accountUpdateRepository);
        this.accountUpdateRepository = accountUpdateRepository;
    }

    @Override
    public Account beforeUpdate(AccountUpdateVo accountUpdateVo, Account account) {
        Account convertedAccount = super.beforeUpdate(accountUpdateVo, account);
        if (account.getId() == null) {
            String pwd = StringUtils.isBlank(account.getPassword()) ? "123456" : account.getPassword();
            account.setPassword(passwordEncoder.encode(pwd));
        }
        return convertedAccount;
    }

    @Override
    public void fillRelateObj(AccountUpdateVo accountUpdateVo, Account account) {
        if (accountUpdateVo.getPostIdList() != null && accountUpdateVo.getPostIdList().size() > 0) {
            //account.setPostSet(postService.findByPostIdIn(accountUpdateVo.getPostIdList()));
        } else {
            account.setPostSet(null);
        }
        if (accountUpdateVo.getRoleIdList() != null && accountUpdateVo.getRoleIdList().size() > 0) {
            //account.setRoleSet(roleService.findByRoleIdIn(accountUpdateVo.getRoleIdList()));
        } else {
            account.setRoleSet(null);
        }
    }

    @Override
    public void postUpdate(Account account, AccountUpdateVo accountUpdateVo) {
        if (accountUpdateVo.getDepartId() != null) {

            Optional<Department> departmentOptional = getDepartmentById(accountUpdateVo.getDepartId());
            if (departmentOptional.isPresent()) {
                Department department = departmentOptional.get();
                department.addAccount(account);
            }
        }
    }

    @Override
    public UniqueInfo checkUnique(AccountUpdateVo accountUpdateVo, Account account) {
        Long count = StringUtils.isBlank(accountUpdateVo.getId()) ? accountQueryRepository.countByLoginName(accountUpdateVo.getLoginName()) : accountQueryRepository.countByLoginNameAndIdNot(accountUpdateVo.getLoginName(), Long.valueOf(accountUpdateVo.getId()));
        if (count > 0) {
            return UniqueInfo.getUniqueInfo("登录名称重复");
        }
        return super.checkUnique(accountUpdateVo, account);
    }

    private Optional<Department> getDepartmentById(Long departId) {
        return departQueryRepository.findById(departId);
    }

    @Transactional(rollbackFor = {BusinessException.class, EntityNotFoundException.class})
    @Override
    public void updateAccountWithDepart(Long userId, Long departId) {
        Account account = accountQueryRepository.getOne(userId);
        if (account == null) {
            throw new BusinessException(ErrorNum.ERROR_NOT_FOUND_DATA);
        }
        Department department = departQueryRepository.getOne(departId);
        if (department == null) {
            throw new BusinessException(ErrorNum.ERROR_NOT_FOUND_DATA);
        }
//        Department referenceDepart = entityManager.getReference(Department.class,departId);

        department.addAccount(account);

    }
}
