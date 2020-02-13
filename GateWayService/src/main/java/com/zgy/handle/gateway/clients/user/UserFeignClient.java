package com.zgy.handle.gateway.clients.user;

import com.zgy.handle.gateway.model.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("userservice")
public interface UserFeignClient {
    @RequestMapping(method = RequestMethod.POST,value = "/account/findAccountByLoginName")
    UserInfo getUserInfo(@RequestBody String loginName);
}
