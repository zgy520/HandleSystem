package com.zgy.handle.userService.service.user.query;

import com.zgy.handle.userService.model.authority.Post;
import com.zgy.handle.userService.model.authority.Role;
import com.zgy.handle.userService.model.structure.Department;
import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.model.user.Account_;
import com.zgy.handle.userService.model.user.cross.RolePostDTO;
import com.zgy.handle.userService.model.user.query.AccountQueryVo;
import com.zgy.handle.userService.repository.user.AccountRepository;
import com.zgy.handle.userService.repository.user.query.AccountQueryRepository;
import com.zgy.handle.userService.service.base.impl.QueryServiceImpl;
import com.zgy.handle.userService.service.structure.DepartmentAccountService;
import com.zgy.handle.userService.service.structure.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AccountQueryServiceImpl extends QueryServiceImpl<Account, AccountQueryVo> implements AccountQueryService {
    @Autowired
    private DepartmentAccountService departmentAccountService;
    private final AccountQueryRepository accountQueryRepository;
    @Autowired
    public AccountQueryServiceImpl(AccountQueryRepository accountQueryRepository) {
        super(accountQueryRepository);
        this.accountQueryRepository = accountQueryRepository;
    }

    @Override
    public Page<Account> findByDynamicQuery(Pageable pageable, AccountQueryVo dto) {
        Specification<Account> specification = Specification
                .where(StringUtils.isBlank(dto.getName())? null : AccountRepository.nameContains(dto.getName()))
                .and(StringUtils.isBlank(dto.getLoginName())? null : AccountRepository.nameContains(dto.getLoginName()))
                .and(StringUtils.isBlank(dto.getDepartName())? null : AccountRepository.departNameLike(dto.getDepartName()))
                .and(StringUtils.isBlank(dto.getRoleName())? null : AccountRepository.roleLike(dto.getRoleName()))
                .and(StringUtils.isNotBlank(dto.getPostName())?AccountRepository.postLike(dto.getPostName()) : null);
        return accountQueryRepository.findAll(specification,pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public RolePostDTO fetchRolePostName(Long userId){
        Account account = this.accountQueryRepository.findById(userId).get();
        List<String> roleList = account.getRoleSet().stream().map(Role::getName).map(String::valueOf).collect(Collectors.toList());
        List<String> roleIdList = account.getRoleSet().stream().map(Role::getId).map(String::valueOf).collect(Collectors.toList());
        List<String> postList = account.getPostSet().stream().map(Post::getName).map(String::valueOf).collect(Collectors.toList());
        List<String> postIdList = account.getPostSet().stream().map(Post::getId).map(String::valueOf).collect(Collectors.toList());
        Department department = departmentAccountService.getByAccountId(userId);
        RolePostDTO rolePostDTO = new RolePostDTO(roleList,roleIdList,postList,postIdList,department==null?"":department.getId().toString(),department==null?"":department.getName());
        return rolePostDTO;
    }
}
