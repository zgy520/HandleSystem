package com.zgy.handle.userservice.controller.user.query;

import cn.hutool.core.date.StopWatch;
import com.zgy.handle.common.controller.base.BaseQueryController;
import com.zgy.handle.common.model.common.SelectDTO;
import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userservice.model.user.Account;
import com.zgy.handle.userservice.model.user.query.AccountQueryVo;
import com.zgy.handle.userservice.service.user.query.AccountQueryService;
import com.zgy.handle.userservice.service.user.update.AccountUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author a4423
 */
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
    public ResponseCode<List<AccountQueryVo>> list(Pageable pageable, AccountQueryVo dto) {
        ResponseCode<List<AccountQueryVo>> responseCode = ResponseCode.sucess();
        pageable = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize(), pageable.getSort());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Page<Account> page = accountQueryService.findByDynamicQuery(pageable, dto);
        stopWatch.stop();
        log.info("获取列表所用的时间为:" + stopWatch.getTotalTimeMillis() / 1000);
        List<Account> contentList = page.getContent();
        List<AccountQueryVo> dtoList = convertTtoU(contentList);
        responseCode.setPageInfo(page);
        fillList(contentList, dtoList);
        responseCode.setData(dtoList);
        return responseCode;
    }

    @Override
    public void fillList(List<Account> entityList, List<AccountQueryVo> dtoList) {
        /*Instant start = Instant.now();
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
        log.info("所用时间为:" + Duration.between(start, end));*/
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

    @GetMapping(value = "getAllAccount")
    public ResponseCode<List<Account>> getAllAccount() {
        ResponseCode<List<Account>> responseCode = ResponseCode.sucess();
        responseCode.setData(accountQueryService.findAllAccountByXml());
        return responseCode;
    }
}
