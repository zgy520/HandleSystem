package com.zgy.handle.userService.service.user;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userService.model.authority.Post;
import com.zgy.handle.userService.model.authority.Role;
import com.zgy.handle.userService.model.authority.RoleDTO;
import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.model.user.AccountDTO;
import com.zgy.handle.userService.model.user.cross.RolePostDTO;
import com.zgy.handle.userService.repository.user.AccountRepository;
import com.zgy.handle.userService.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccountService extends SystemService<Account> {
    private AccountRepository accountRepository;
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



    public Page<Account> findAllByDynamicQuery(Pageable pageable, AccountDTO accountDTO){
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
        RolePostDTO rolePostDTO = new RolePostDTO(roleList,postList);
        responseCode.setData(rolePostDTO);
        return responseCode;
    }
    
}
