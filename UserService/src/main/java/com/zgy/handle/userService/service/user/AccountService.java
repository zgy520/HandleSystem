package com.zgy.handle.userService.service.user;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userService.model.authority.Post;
import com.zgy.handle.userService.model.authority.Role;
import com.zgy.handle.userService.model.structure.Department;
import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.model.user.AccountDTO;
import com.zgy.handle.userService.model.user.cross.RolePostDTO;
import com.zgy.handle.userService.repository.user.AccountRepository;
import com.zgy.handle.userService.service.SystemRefactorService;
import com.zgy.handle.userService.service.authority.RoleService;
import com.zgy.handle.userService.service.authority.post.PostService;
import com.zgy.handle.userService.service.structure.DepartmentAccountService;
import com.zgy.handle.userService.service.structure.DepartmentService;
import com.zgy.handle.userService.util.Str.StrUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccountService extends SystemRefactorService<Account,AccountDTO> {
    private AccountRepository accountRepository;
    @Autowired
    private DepartmentAccountService departmentAccountService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PostService postService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    public AccountService(AccountRepository accountRepository){
        super(accountRepository);
        this.accountRepository = accountRepository;
    }

    public Account findByLoginName(String loginName){
        return this.accountRepository.findByLoginName(loginName);
    }

    /**
     * 根据id列表获取所有的账户
     * @param idList
     * @return
     */
    public Set<Account> findByIdIn(List<Long> idList){
        Set<Account> accountSet = accountRepository.findAllByIdIn(idList);
        return accountSet;
    }

    public List<Account> findAllAccounts(){
        return accountRepository.findAll();
    }


    @Override
    public Page<Account> findByDynamicQuery(Pageable pageable, AccountDTO accountDTO){
        Specification<Account> specification = Specification
                .where(accountDTO.getName() == null? null : AccountRepository.nameContains(accountDTO.getName()))
                .and(accountDTO.getLoginName() == null? null : AccountRepository.fieldContains("loginName",accountDTO.getLoginName()));
        return accountRepository.findAll(specification,pageable);
    }

    public Optional<Account> findOne(Long id){
        Account account = new Account();
        account.setId(id);
        Example<Account> example = Example.of(account);
        return accountRepository.findOne(example);
    }

    @Transactional(readOnly = true)
    public ResponseCode<RolePostDTO> fetchRolePostListByUserId(Long userId){
        ResponseCode<RolePostDTO> responseCode = ResponseCode.sucess();
        Account account = this.accountRepository.findById(userId).get();
        List<String> roleList = account.getRoleSet().stream().map(Role::getId).map(String::valueOf).collect(Collectors.toList());
        List<String> postList = account.getPostSet().stream().map(Post::getId).map(String::valueOf).collect(Collectors.toList());
        Department department = departmentAccountService.getByAccountId(userId);
        RolePostDTO rolePostDTO = new RolePostDTO(roleList,postList,department==null?"":department.getId().toString());
        responseCode.setData(rolePostDTO);
        return responseCode;
    }

    @Transactional(readOnly = true)
    public String fetchPostIdListByAccountId(Long userId){
        Account account = this.accountRepository.findById(userId).get();
        String postList = account.getPostSet().stream().map(Post::getId).map(String::valueOf).collect(Collectors.joining(","));
        return postList;
    }
    @Transactional(readOnly = true)
    public Set<String> fetchRoleCodeListByAccountId(Long userId){
        Account account = this.accountRepository.findById(userId).get();
        Set<String> roleList = account.getRoleSet().stream().map(Role::getCode).collect(Collectors.toSet());
        return roleList;
    }

    @Override
    public void beforeUpdate(AccountDTO accountDTO, Account account) {
        List<Long> roleIdList = StrUtils.transformList(accountDTO.getRoleList(), Long::parseLong);
        List<Long> postIdList = StrUtils.transformList(accountDTO.getPostList(), Long::parseLong);

        account.setRoleSet(roleService.findByRoleIdIn(roleIdList));
        account.setPostSet(postService.findByPostIdIn(postIdList));
        account.setPassword(passwordEncoder.encode("123456"));

    }

    @Override
    public void postUpdate(Account account,AccountDTO accountDTO) {
        if (StringUtils.isNotBlank(accountDTO.getDepartId())) {
            Optional<Department> departmentOptional = departmentService.findById(Long.valueOf(accountDTO.getDepartId()));
            if (departmentOptional.isPresent()) {
                departmentAccountService.deleteByAccountId(account.getId());
                Department department = departmentOptional.get();
                //log.info("获取到的部门信息为: " + department);
                departmentAccountService.setDepartmentAccount(account, department);
            }
        }
    }
}
