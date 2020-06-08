package com.zgy.handle.userService.controller.structure;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zgy.handle.userService.model.structure.Enterprise;
import com.zgy.handle.userService.service.structure.SyncEnterpriseToRootService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "syncRoot")
@Slf4j
@RestController
public class SyncRootController {
    @Autowired
    private SyncEnterpriseToRootService syncEnterpriseToRootService;

    @PostMapping(value = "enterprise")
    public String syncRoot(@RequestBody String enterpriseInfo) throws JsonProcessingException {
        log.info(enterpriseInfo);
        ObjectMapper mapper = new ObjectMapper();
        Enterprise enterprise = mapper.readValue(enterpriseInfo,Enterprise.class);
        log.info(enterprise.getAuthorStatus());
        return "success";
    }

    @GetMapping(value = "ep")
    public void epInfo(){
        syncEnterpriseToRootService.syncEnterpriseInfo(1l);
    }
}
