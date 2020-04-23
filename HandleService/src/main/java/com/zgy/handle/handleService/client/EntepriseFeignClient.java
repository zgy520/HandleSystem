package com.zgy.handle.handleService.client;

import com.zgy.handle.handleService.model.meta.structure.enterprise.EnterprisePre;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("userservice")
public interface EntepriseFeignClient {
    @RequestMapping(method = RequestMethod.GET, value = "/enterprise/getEnterprisePre")
    EnterprisePre getEnterpriseInfo(@RequestBody String enterpriseId);
}
