package com.zgy.handle.userService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("handleservice")
public interface SecondRootFeignClient {
    /**
     * 获取根服务器的地址，用于企业信息同步
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/secondRoot/getParentServer")
    String getParentServer();
}
