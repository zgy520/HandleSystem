package com.zgy.handle.userservice.controller.user.query;

import com.zgy.handle.common.controller.base.BaseQueryController;
import com.zgy.handle.common.model.common.SelectDTO;
import com.zgy.handle.userservice.model.user.Account;
import com.zgy.handle.userservice.model.user.cross.RolePostDepartDTO;
import com.zgy.handle.userservice.model.user.query.AccountQueryVo;
import com.zgy.handle.userservice.service.user.query.AccountQueryService;
import com.zgy.handle.userservice.service.user.update.AccountUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "account/query")
@Slf4j
public class AccountQueryController extends BaseQueryController<Account, AccountQueryVo> {
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
        Instant start = Instant.now();
        dtoList.stream().forEach(dto -> {
            RolePostDepartDTO rolePostDepartDTO = accountQueryService.fetchRolePostName(Long.valueOf(dto.getId()));
            if (rolePostDepartDTO != null) {
                dto.setRoleList(rolePostDepartDTO.getRoleList());
                dto.setPostList(rolePostDepartDTO.getPostList());
                dto.setRoleIdList(rolePostDepartDTO.getRoleIdList());
                dto.setPostIdList(rolePostDepartDTO.getPostIdList());
                dto.setDepartId(rolePostDepartDTO.getDepartId());
                dto.setDepartName(rolePostDepartDTO.getDepartName());
            }
        });
        Instant end = Instant.now();
        log.info("所用时间为:" + Duration.between(start, end));
    }

    @Override
    public List<SelectDTO> convertTtoSelectDTOList(List<Account> accountList) {
        List<SelectDTO> selectDTOList = new ArrayList<>();
        accountList.stream().forEach(account -> {
            SelectDTO selectDTO = new SelectDTO();
            selectDTO.setLabel(account.getName());
            selectDTO.setValue(account.getId().toString());
            selectDTO.setKey(account.getId().toString());
            selectDTOList.add(selectDTO);
        });
        return selectDTOList;
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
