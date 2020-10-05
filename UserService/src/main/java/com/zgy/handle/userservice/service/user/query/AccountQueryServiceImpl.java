package com.zgy.handle.userservice.service.user.query;

import com.zgy.handle.common.service.base.impl.BaseQueryServiceImpl;
import com.zgy.handle.userservice.core.error.ErrorNum;
import com.zgy.handle.userservice.core.exception.BusinessException;
import com.zgy.handle.userservice.model.authority.Post;
import com.zgy.handle.userservice.model.authority.role.Role;
import com.zgy.handle.userservice.model.structure.Department;
import com.zgy.handle.userservice.model.user.Account;
import com.zgy.handle.userservice.model.user.Account_;
import com.zgy.handle.userservice.model.user.RoleAccountDTO;
import com.zgy.handle.userservice.model.user.cross.RolePostDepartDTO;
import com.zgy.handle.userservice.model.user.query.AccountQueryVo;
import com.zgy.handle.userservice.repository.authority.role.RoleQueryRepository;
import com.zgy.handle.userservice.repository.user.query.AccountQueryRepository;
import com.zgy.handle.userservice.service.structure.depart.query.DepartAccountQueryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author a4423
 */
@Service
@Slf4j
public class AccountQueryServiceImpl extends BaseQueryServiceImpl<Account, AccountQueryVo> implements AccountQueryService {
    @PersistenceContext
    private EntityManager entityManager;


     @Autowired
     private DepartAccountQueryService departAccountQueryService;
    private final AccountQueryRepository accountQueryRepository;

    @Autowired
    public AccountQueryServiceImpl(AccountQueryRepository accountQueryRepository) {
        super(accountQueryRepository);
        this.accountQueryRepository = accountQueryRepository;
    }

    @Override
    public Specification<Account> querySpecification(AccountQueryVo dto) {
        Specification<Account> specification = Specification
                .where(StringUtils.isBlank(dto.getName()) ? null : accountQueryRepository.fieldLike(Account_.NAME, dto.getName()))
                .and(StringUtils.isBlank(dto.getLoginName()) ? null : accountQueryRepository.fieldLike(Account_.LOGIN_NAME,dto.getLoginName()))
                .and(StringUtils.isBlank(dto.getDepartName()) ? null : AccountQueryRepository.departNameLike(dto.getDepartName()))
                .and(StringUtils.isBlank(dto.getRoleName()) ? null : AccountQueryRepository.roleNameLike(dto.getRoleName()))
                .and(StringUtils.isNotBlank(dto.getPostName()) ? AccountQueryRepository.postNameLike(dto.getPostName()) : null);
        return specification;
    }

    @Transactional(readOnly = true)
    @Override
    public RolePostDepartDTO fetchRolePostName(Long userId) {
        Optional<Account> accountOptional = this.accountQueryRepository.findById(userId);
        if (!accountOptional.isPresent()) {
            throw new BusinessException(ErrorNum.ERROR_LOGIN_LOGINNAME_NOT_FOUNT);
        }
        Account account = accountOptional.get();
        List<String> roleList = account.getRoleSet().stream().map(Role::getName).map(String::valueOf).collect(Collectors.toList());
        List<String> roleIdList = account.getRoleSet().stream().map(Role::getId).map(String::valueOf).collect(Collectors.toList());
        List<String> postList = account.getPostSet().stream().map(Post::getName).map(String::valueOf).collect(Collectors.toList());
        List<String> postIdList = account.getPostSet().stream().map(Post::getId).map(String::valueOf).collect(Collectors.toList());
        Department department =departAccountQueryService.getByAccountId(userId);
        RolePostDepartDTO rolePostDepartDTO = new RolePostDepartDTO(roleList, roleIdList, postList, postIdList, department == null ? "" : department.getId().toString(), department == null ? "" : department.getName());
        return rolePostDepartDTO;
    }

    /**
     * 获取带有角色的账号信息
     *
     * @param accountId
     * @return
     */
    @Override
    public Account getAccountWithRole(Long accountId) {
        TypedQuery<Account> query = entityManager.createQuery(" select account from Account account JOIN FETCH account.roleSet where account.id = :accountId"
                , Account.class);
        query.setParameter("accountId", accountId);
        Account account = query.getSingleResult();
        return account;
    }

    @Override
    public Account findByLoginName(String loginName) {
        return accountQueryRepository.findByLoginName(loginName);
    }

    @Override
    public List<Account> findAllAccountByXml() {
        Query query = entityManager.createNamedQuery("findAccountByNativeSql");
        List<RoleAccountDTO> accountList = query.getResultList();
        //List<Account> accountNativeList = accountQueryRepository.findAccountByNativeSql();
       // List<Role> roleList = roleQueryRepository.findRoleList();
        log.info("获取到的账号的数量为:" + accountList.size() + "个");
        return null;
    }


    private Account getAccountWithAll(Long accountId) {
        TypedQuery<Account> query = entityManager.createQuery(" select account from Account account left JOIN FETCH account.roleSet left JOIN FETCH account.postSet left JOIN FETCH account.departmentAccounts where account.id = :accountId"
                , Account.class);
        query.setParameter("accountId", accountId);
        Account account = query.getResultStream().findFirst().orElse(null);
        return account;
    }
}
