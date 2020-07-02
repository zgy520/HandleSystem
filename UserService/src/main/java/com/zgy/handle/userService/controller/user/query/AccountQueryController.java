package com.zgy.handle.userService.controller.user.query;

import com.zgy.handle.userService.controller.base.QueryController;
import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.model.user.SelectDTO;
import com.zgy.handle.userService.model.user.cross.RolePostDTO;
import com.zgy.handle.userService.model.user.query.AccountQueryVo;
import com.zgy.handle.userService.service.user.query.AccountQueryService;
import com.zgy.handle.userService.service.user.update.AccountUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "account/query")
@Slf4j
public class AccountQueryController extends QueryController<Account, AccountQueryVo> {
    @Autowired
    private AccountQueryMapper accountQueryMapper;

    private final AccountQueryService accountQueryService;
    private final AccountUpdateService accountUpdateService;
    @Autowired
    public AccountQueryController(AccountQueryService accountQueryService, AccountUpdateService accountUpdateService) {
        super(accountUpdateService, accountQueryService);
        this.accountQueryService = accountQueryService;
        this.accountUpdateService = accountUpdateService;
    }

    @Override
    public void fillList(List<Account> entityList, List<AccountQueryVo> dtoList) {
        dtoList.stream().forEach(dto->{
            RolePostDTO rolePostDTO = accountQueryService.fetchRolePostName(Long.valueOf(dto.getId()));
            dto.setRoleList(rolePostDTO.getRoleList());
            dto.setPostList(rolePostDTO.getPostList());
            dto.setRoleIdList(rolePostDTO.getRoleIdList());
            dto.setPostIdList(rolePostDTO.getPostIdList());
            dto.setDepartId(rolePostDTO.getDepartId());
            dto.setDepartName(rolePostDTO.getDepartName());
        });
    }

    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<Account> accountList) {
        return null;
    }

    @Override
    public List<AccountQueryVo> convertTtoU(List<Account> accountList) {
        return accountQueryMapper.toAccountQueryVoList(accountList);
    }

    @Override
    public AccountQueryVo convertTtoU(Account account) {
        return accountQueryMapper.toAccountQueryVo(account);
    }

    @Override
    public Account convertUtoT(AccountQueryVo accountQueryVo) {
        return accountQueryMapper.toAccount(accountQueryVo);
    }
}
