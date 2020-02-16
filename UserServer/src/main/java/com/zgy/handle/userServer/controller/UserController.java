package com.zgy.handle.userServer.controller;

import com.zgy.handle.common.zuul.context.UserContext;
import com.zgy.handle.userServer.model.ResponseCode;
import com.zgy.handle.userServer.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "user")
@Slf4j
public class UserController {
    @Autowired
    private HttpServletRequest request;
    /*@GetMapping(value = "login")
    public String login(String loginName,String password){
      log.info(String.format("验证得用户名为:%s,密码为:%s",loginName,password));
      return "processing!";
    }*/

    @GetMapping(value = "test")
    public String test(){
        return "test sucessfully!";
    }

    @GetMapping(value = "info")
    public ResponseCode<UserInfo> getUserInfo(){
        log.info("获取的userId的值为:" + request.getHeader(UserContext.USER_ID));
        ResponseCode<UserInfo> responseCode = ResponseCode.sucess();
        UserInfo userInfo = new UserInfo();
        userInfo.setName("超级管理员");
        userInfo.setIntroduction("come from back system!");
        userInfo.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        Set<String> roleSet = new HashSet<>();
        roleSet.add("admin");
        roleSet.add("editor");
        userInfo.setRoleSet(roleSet);
        responseCode.setData(userInfo);
        return responseCode;
    }
    @PostMapping(value = "logout")
    public ResponseCode<String> userLogout(){
        ResponseCode<String> responseCode = ResponseCode.sucess();
        responseCode.setData("sucess");
        return responseCode;
    }

}
