package com.zgy.handle.userService.controller.user;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userService.controller.user.convert.AccountMapper;
import com.zgy.handle.userService.model.user.Account;
import com.zgy.handle.userService.model.user.AccountDTO;
import com.zgy.handle.userService.model.user.SelectDTO;
import com.zgy.handle.userService.model.user.UserInfo;
import com.zgy.handle.userService.model.user.cross.RolePostDTO;
import com.zgy.handle.userService.service.authority.RoleService;
import com.zgy.handle.userService.service.authority.post.PostService;
import com.zgy.handle.userService.service.user.AccountService;
import com.zgy.handle.userService.util.Str.StrUtils;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.*;

/**
 * 用户验证的类
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "account")
public class AccountController {
    private final AccountService accountService;
    private final AccountMapper accountMapper;
    private final RoleService roleService;
    private final PostService postService;
    //private final SelectAccountMapper selectAccountMapper;

    @PostMapping(value = "findAccountByLoginName")
    public UserInfo findByLoginName(@RequestBody String loginName){
        Account account = this.accountService.findByLoginName(loginName);
        if (account == null){
            return null;
        }
        UserInfo userInfo = UserInfo.builder()
                .userName(account.getLoginName())
                .pasword(account.getPassword())
                .userId(account.getId().toString())
                .orgId("222")
                .postId("333")
                .build();
        Set<String> roleSet = new HashSet<>();
        roleSet.add("admin");
        roleSet.add("general");
        userInfo.setRoleSet(roleSet);

        return userInfo;
    }

    /**
     * 获取角色列表
     * @param pageable
     * @param accountDTO
     * @return
     */
    @GetMapping(value = "list")
    public ResponseCode<List<AccountDTO>> getAccountList(@PageableDefault(page = 1,size = 10) Pageable pageable, AccountDTO accountDTO){
        pageable = PageRequest.of(pageable.getPageNumber() -1, pageable.getPageSize(), Sort.Direction.DESC,"updateTime");
        ResponseCode<List<AccountDTO>> responseCode = ResponseCode.sucess();

        Page<Account> roleList = accountService.findAllByDynamicQuery(pageable,accountDTO);
        List<Account> roles = roleList.getContent();
        List<AccountDTO> roleDTOList = accountMapper.toAccountDTOs(roles);
        responseCode.setPageInfo(roleList);
        responseCode.setData(roleDTOList);
        return responseCode;
    }

    @PostMapping(value = "update")
    public ResponseCode<AccountDTO> update(@RequestBody AccountDTO accountDTO){
        ResponseCode<AccountDTO> responseCode = ResponseCode.sucess();
        log.info("获取到的数据为:" + accountDTO);
        Account account = accountMapper.toAccount(accountDTO);
        List<Long> roleIdList = StrUtils.transformList(accountDTO.getRoleList(),Long::parseLong);
        List<Long> postIdList = StrUtils.transformList(accountDTO.getPostList(),Long::parseLong);
        account.setRoleSet(roleService.findByRoleIdIn(roleIdList));
        account.setPostSet(postService.findByPostIdIn(postIdList));
        accountService.update(StringUtils.isBlank(accountDTO.getId())? null : Long.valueOf(accountDTO.getId()),account);
        responseCode.setData(accountDTO);
        return responseCode;
    }

    @ApiOperation("根据id获取数据")
    @GetMapping(value = "find/{id}")
    public ResponseCode<AccountDTO> findById(@PathVariable(value = "id") Long id){
        ResponseCode<AccountDTO> responseCode = ResponseCode.sucess();
        Optional<Account> optionalRole = accountService.findById(id);
        if (!optionalRole.isPresent()){
            throw new EntityNotFoundException("角色中对应id={" + id + "}的数据不存在");
        }
        responseCode.setData(accountMapper.toAccountDTO(optionalRole.get()));
        return responseCode;
    }

    @ApiOperation("删除账号")
    @DeleteMapping(value = "delete/{id}")
    public ResponseCode<AccountDTO> delete(@PathVariable(value = "id") Long id){
        Optional<Account> optionalRole = accountService.findById(id);
        ResponseCode<AccountDTO> responseCode = ResponseCode.sucess();
        if (optionalRole.isPresent())
            responseCode.setData(accountMapper.toAccountDTO(optionalRole.get()));
        else
            throw new EntityNotFoundException("找不到id对应为:" + id.toString() + "的值");
        accountService.delete(id);
        return responseCode;
    }

    @GetMapping(value = "getAccountList")
    public ResponseCode<List<SelectDTO>> getAccountList(){
        ResponseCode<List<SelectDTO>> responseCode = ResponseCode.sucess();
        List<Account> accounts = accountService.findAllAccounts();
        List<SelectDTO> accountSelectDTOList = new ArrayList<>();
        accounts.stream().forEach(account -> {
            SelectDTO accountSelectDTO = new SelectDTO(account.getId().toString(),account.getName());
            accountSelectDTOList.add(accountSelectDTO);
        });
        responseCode.setData(accountSelectDTOList);
        return responseCode;
    }


    @GetMapping(value = "getRolePostListById/{id}")
    public ResponseCode<RolePostDTO> getRolePostListById(@PathVariable(value = "id") Long id){
        return accountService.fetchRolePostListByUserId(id);
    }
}
