package com.zgy.handle.userservice.controller.user;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.common.zuul.context.UserContext;
import com.zgy.handle.userservice.dto.RoleMenuBtnDTO;
import com.zgy.handle.userservice.model.user.Account;
import com.zgy.handle.userservice.model.user.UserDisplayInfo;
import com.zgy.handle.userservice.service.authority.role.query.RoleQueryService;
import com.zgy.handle.userservice.service.user.query.AccountQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息
 * @author a4423
 */
@RestController
@RequestMapping(value = "userInfo")
@Slf4j
public class UserDisplayController {

    @Autowired
    private AccountQueryService accountQueryService;
    @Autowired
    private RoleQueryService roleQueryService;
    @Autowired
    private HttpServletRequest request;

    @GetMapping(value = "info")
    public ResponseCode<UserDisplayInfo> getUserInfo() {
        log.info("获取的userId的值为:" + request.getHeader(UserContext.USER_ID)
                + ",postId为: " + request.getHeader(UserContext.POST_ID) + ",机构id为:" + request.getHeader(UserContext.ORG_ID));
        ResponseCode<UserDisplayInfo> responseCode = ResponseCode.sucess();
        //Optional<Account> accountOptional = accountService.findById(Long.valueOf(request.getHeader(UserContext.USER_ID)));
        Account accountWithRole = accountQueryService.getAccountWithRole(Long.valueOf(request.getHeader(UserContext.USER_ID)));
        if (accountWithRole != null){
            List<Long> roleIdList = new ArrayList<>(); // 所关联的RoleID
            String RoleIDString = "";
            accountWithRole.getRoleSet().stream().forEach(menu -> {
                roleIdList.add(menu.getId());

            });
            for (int a = 0 ; a < roleIdList.size() ; a++){
                RoleIDString= roleIdList.get(a)+","+RoleIDString;
            }

            //Account account = accountOptional.get();
            Account account = accountWithRole;
            UserDisplayInfo userDisplayInfo = new UserDisplayInfo();
            userDisplayInfo.setName(account.getName());
            userDisplayInfo.setIntroduction(account.getNote());
            userDisplayInfo.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
            //Set<String> roleSet = accountService.fetchRoleCodeListByAccountId(account.getId());
            userDisplayInfo.setRoleID(roleIdList); // q
            userDisplayInfo.setRoleIDString(RoleIDString);
            userDisplayInfo.setRoles(account.getRoleSet());
//            userDisplayInfo.setRoleMenuBtnDTO(roleQueryService.getPowerByRoleIdIn(roleIdList)); // 需要展示的页面和按钮
            List<RoleMenuBtnDTO> roleMenuBtnDTOList = new ArrayList<>();
            RoleMenuBtnDTO roleMenuBtnDTO = new RoleMenuBtnDTO();
            roleMenuBtnDTO.setId("1");
            roleMenuBtnDTO.setMenuId(1L);
            roleMenuBtnDTO.setMenuName("测试");
            roleMenuBtnDTO.setMenuURL("/menu/test");
            roleMenuBtnDTOList.add(roleMenuBtnDTO);
            userDisplayInfo.setRoleMenuBtnDTO(roleMenuBtnDTOList);
            responseCode.setData(userDisplayInfo);
        }else {
            throw new EntityNotFoundException("未找到对应的用户信息");
        }

        return responseCode;
    }

    @PostMapping(value = "logout")
    public ResponseCode<String> userLogout() {
        ResponseCode<String> responseCode = ResponseCode.sucess();

        responseCode.setData("sucess");
        return responseCode;
    }
}
