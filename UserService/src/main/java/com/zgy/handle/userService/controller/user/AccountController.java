package com.zgy.handle.userService.controller.user;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userService.controller.SystemController;
import com.zgy.handle.userService.controller.user.convert.AccountMapper;
import com.zgy.handle.userService.model.structure.Department;
import com.zgy.handle.userService.model.structure.Industry;
import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.model.user.AccountDTO;
import com.zgy.handle.userService.model.user.SelectDTO;
import com.zgy.handle.userService.model.user.UserInfo;
import com.zgy.handle.userService.model.user.cross.RolePostDTO;
import com.zgy.handle.userService.service.authority.RoleService;
import com.zgy.handle.userService.service.authority.post.PostService;
import com.zgy.handle.userService.service.structure.DepartmentAccountService;
import com.zgy.handle.userService.service.structure.DepartmentService;
import com.zgy.handle.userService.service.user.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户验证的类
 */
@RestController
@Slf4j
@RequestMapping(value = "account")
public class AccountController extends SystemController<Account,AccountDTO> {
    private final AccountService accountService;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private DepartmentAccountService departmentAccountService;
    @Autowired
    private DepartmentService departmentService;


    public AccountController(AccountService accountService) {
        super(accountService);
        this.accountService = accountService;
    }

    @Override
    public void fillList(List<Account> entityList, List<AccountDTO> dtoList) {
        dtoList.stream().forEach(dto->{
            RolePostDTO rolePostDTO = accountService.fetchRolePostName(Long.valueOf(dto.getId()));
            dto.setRoleList(rolePostDTO.getRoleList());
            dto.setPostList(rolePostDTO.getPostList());
            dto.setDepartName(rolePostDTO.getDepartName());
        });
    }

    @PostMapping(value = "findAccountByLoginName")
    public UserInfo findByLoginName(@RequestBody String loginName) {
        Account account = this.accountService.findByLoginName(loginName);
        if (account == null) {
            return null;
        }
        Department department = departmentAccountService.getByAccountId(account.getId());
        ResponseCode<RolePostDTO> responseCode = accountService.fetchRolePostListByUserId(account.getId());
        UserInfo userInfo = UserInfo.builder()
                .userName(account.getLoginName())
                .pasword(account.getPassword())
                .userId(account.getId().toString())
                .orgId(department == null?"":department.getId().toString())
                .enterpriseId(department == null?"":departmentService.fetchIndustry(department.getId()).getId().toString())
                .postId(accountService.fetchPostIdListByAccountId(account.getId()))
                .postName(accountService.fetchPostCodeListByAccountId(account.getId()).stream().collect(Collectors.joining(",")))
                .build();
        Set<String> roleSet = accountService.fetchRoleCodeListByAccountId(account.getId());
        userInfo.setRoleSet(roleSet);

        return userInfo;
    }

    @Override
    public List<AccountDTO> convertTtoU(List<Account> accountList) {
        return accountMapper.toAccountDTOs(accountList);
    }

    @Override
    public AccountDTO convertTtoU(Account account) {
        return accountMapper.toAccountDTO(account);
    }

    @Override
    public Account convertUtoT(AccountDTO accountDTO) {
        return accountMapper.toAccount(accountDTO);
    }

    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<Account> accountList) {
        List<SelectDTO> selectDTOList = new ArrayList<>();
        accountList.stream().forEach(account -> {
            SelectDTO selectDTO = new SelectDTO(account.getId().toString(),account.getName());
            selectDTOList.add(selectDTO);
        });
        return selectDTOList;
    }

    @GetMapping(value = "getAccountList")
    public ResponseCode<List<SelectDTO>> getAccountList() {
        ResponseCode<List<SelectDTO>> responseCode = ResponseCode.sucess();
        List<Account> accounts = accountService.findAllAccounts();
        List<SelectDTO> accountSelectDTOList = new ArrayList<>();
        accounts.stream().forEach(account -> {
            SelectDTO accountSelectDTO = new SelectDTO(account.getId().toString(), account.getName());
            accountSelectDTOList.add(accountSelectDTO);
        });
        responseCode.setData(accountSelectDTOList);
        return responseCode;
    }


    @GetMapping(value = "getRolePostListById/{id}")
    public ResponseCode<RolePostDTO> getRolePostListById(@PathVariable(value = "id") Long id) {
        return accountService.fetchRolePostListByUserId(id);
    }

}
