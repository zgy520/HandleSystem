package com.zgy.handle.userservice.service.user.query;

import com.zgy.handle.common.service.base.impl.BaseQueryServiceImpl;
import com.zgy.handle.userservice.model.authority.Post;
import com.zgy.handle.userservice.model.authority.role.Role;
import com.zgy.handle.userservice.model.structure.Department;
import com.zgy.handle.userservice.model.user.Account;
import com.zgy.handle.userservice.model.user.Account_;
import com.zgy.handle.userservice.model.user.cross.RolePostDTO;
import com.zgy.handle.userservice.model.user.query.AccountQueryVo;
import com.zgy.handle.userservice.repository.user.query.AccountQueryRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AccountQueryServiceImpl extends BaseQueryServiceImpl<Account, AccountQueryVo> implements AccountQueryService {
    /* @Autowired
     private DepartmentAccountServiceBase departmentAccountService;*/
    private final AccountQueryRepository accountQueryRepository;

    @Autowired
    public AccountQueryServiceImpl(AccountQueryRepository accountQueryRepository) {
        super(accountQueryRepository);
        this.accountQueryRepository = accountQueryRepository;
    }

   /* @Override
    public Page<Account> findByDynamicQuery(Pageable pageable, AccountQueryVo dto) {
        Specification<Account> specification = Specification
                .where(StringUtils.isBlank(dto.getName())? null : AccountRepository.nameContains(dto.getName()))
                .and(StringUtils.isBlank(dto.getLoginName())? null : AccountRepository.nameContains(dto.getLoginName()))
                .and(StringUtils.isBlank(dto.getDepartName())? null : AccountRepository.departNameLike(dto.getDepartName()))
                .and(StringUtils.isBlank(dto.getRoleName())? null : AccountRepository.roleLike(dto.getRoleName()))
                .and(StringUtils.isNotBlank(dto.getPostName())?AccountRepository.postLike(dto.getPostName()) : null);

        // 个人权限
        Specification<Account> personalPermissionSpec = Specification
                .where(accountQueryRepository.fieldEquals(Account_.CREATED_ID,getPersonalId()));
        // 部门权限
        Specification<Account> departPermissionSpec = Specification
                .where(accountQueryRepository.fieldEquals(Account_.BELONG_ID,getDepartId()));

        // 单位权限
        Specification<Account> unitPermissionSpec = Specification
                .where(accountQueryRepository.fieldIn(Account_.BELONG_ID,"1"));
        Specification<Account> finalSpecification = specification.and(unitPermissionSpec);


        return accountQueryRepository.findAll(finalSpecification,pageable);
    }*/

    @Override
    public Specification<Account> querySpecification(AccountQueryVo dto) {
        Specification<Account> specification = Specification
                .where(StringUtils.isBlank(dto.getName()) ? null : accountQueryRepository.fieldLike(Account_.NAME, dto.getName()));
                /*.and(StringUtils.isBlank(dto.getLoginName()) ? null : AccountRepository.nameContains(dto.getLoginName()))
                .and(StringUtils.isBlank(dto.getDepartName()) ? null : AccountRepository.departNameLike(dto.getDepartName()))
                .and(StringUtils.isBlank(dto.getRoleName()) ? null : AccountRepository.roleLike(dto.getRoleName()))
                .and(StringUtils.isNotBlank(dto.getPostName()) ? AccountRepository.postLike(dto.getPostName()) : null);*/
        return specification;
    }

    @Transactional(readOnly = true)
    @Override
    /*@Cacheable(value = "account", key = "#userId")*/
    public RolePostDTO fetchRolePostName(Long userId) {
        Account account = this.accountQueryRepository.findById(userId).get();
        //Account account = getAccountWithAll(userId);
        if (account == null) {
            return null;
        }
        List<String> roleList = account.getRoleSet().stream().map(Role::getName).map(String::valueOf).collect(Collectors.toList());
        List<String> roleIdList = account.getRoleSet().stream().map(Role::getId).map(String::valueOf).collect(Collectors.toList());
        List<String> postList = account.getPostSet().stream().map(Post::getName).map(String::valueOf).collect(Collectors.toList());
        List<String> postIdList = account.getPostSet().stream().map(Post::getId).map(String::valueOf).collect(Collectors.toList());
        Department department = null; //departmentAccountService.getByAccountId(userId);
        RolePostDTO rolePostDTO = new RolePostDTO(roleList, roleIdList, postList, postIdList, department == null ? "" : department.getId().toString(), department == null ? "" : department.getName());
        return rolePostDTO;
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


    private Account getAccountWithAll(Long accountId) {
        TypedQuery<Account> query = entityManager.createQuery(" select account from Account account left JOIN FETCH account.roleSet left JOIN FETCH account.postSet left JOIN FETCH account.departmentAccounts where account.id = :accountId"
                , Account.class);
        query.setParameter("accountId", accountId);
        Account account = query.getResultStream().findFirst().orElse(null);
        return account;
    }
}
