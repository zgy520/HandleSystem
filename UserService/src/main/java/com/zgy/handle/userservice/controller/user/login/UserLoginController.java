package com.zgy.handle.userservice.controller.user.login;

import com.zgy.handle.userservice.core.error.ErrorNum;
import com.zgy.handle.userservice.core.exception.BusinessException;
import com.zgy.handle.userservice.model.structure.Enterprise;
import com.zgy.handle.userservice.model.user.Account;
import com.zgy.handle.userservice.model.user.UserInfo;
import com.zgy.handle.userservice.model.user.cross.RolePostDepartDTO;
import com.zgy.handle.userservice.service.structure.depart.query.DepartAccountQueryService;
import com.zgy.handle.userservice.service.structure.depart.query.DepartQueryService;
import com.zgy.handle.userservice.service.user.query.AccountQueryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/**
 * @author: a4423
 * @date: 2020/10/5 8:33
 */
@RestController
@RequestMapping(value = "account")
@Slf4j
public class UserLoginController {
    @Autowired
    private AccountQueryService accountQueryService;
    @Autowired
    private DepartAccountQueryService departAccountQueryService;
    @Autowired
    private DepartQueryService departQueryService;


    /**
     * 根据用户名获取用户信息
     * @param loginName 登录名
     * @return 用户信息
     */
    @PostMapping(value = "findAccountByLoginName")
    public UserInfo findAccountByLoginName(@RequestBody String loginName){
        Account account = accountQueryService.findByLoginName(loginName);
        if (account == null){
            throw new BusinessException(ErrorNum.ERROR_LOGIN_LOGINNAME_NOT_FOUNT);
        }
        RolePostDepartDTO rolePostDepartDTO = accountQueryService.fetchRolePostName(account.getId());
        String enterpriseId = "";
        if (StringUtils.isNotBlank(rolePostDepartDTO.getDepartId())){
            Enterprise enterprise = departQueryService.fetchIndustry(Long.valueOf(rolePostDepartDTO.getDepartId()));
            enterpriseId = enterprise.getId().toString();
        }


        UserInfo userInfo = UserInfo.builder()
                    .userName(account.getLoginName())
                    .pasword(account.getPassword())
                    .userId(account.getId().toString())
                    .orgId(rolePostDepartDTO.getDepartId())
                    .enterpriseId(enterpriseId)
                    .postId(rolePostDepartDTO.getPostIdList().stream().collect(Collectors.joining(",")))
                    .postName(rolePostDepartDTO.getPostList().stream().collect(Collectors.joining(",")))
                    .roles(rolePostDepartDTO.getRoleList().stream().collect(Collectors.toSet()))
                    .build();

        return userInfo;
    }
}
