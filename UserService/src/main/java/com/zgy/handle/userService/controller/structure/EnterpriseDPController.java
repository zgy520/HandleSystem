package com.zgy.handle.userService.controller.structure;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zgy.handle.userService.service.structure.EnterpriseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "dpEnterprise")
@Slf4j
@Api(tags = "大屏部分的企业信息获取")
@CrossOrigin(origins = "*")
public class EnterpriseDPController {
    @Autowired
    private EnterpriseService enterpriseService;

    @GetMapping(value = "getEnterpriseStatic")
    @ApiOperation(value = "获取行业统计信息")
    public JSONObject getEnterpriseStatic(){
        return enterpriseService.industryStatic();
    }

    @GetMapping(value = "getEnterprisexxList")
    @ApiOperation(value = "获取企业列表")
    public JSONArray getEnterpriseList(){
        return enterpriseService.getEnterpriseInfo();
    }

    @GetMapping(value = "getEnterpriseProvinceInfo")
    @ApiOperation(value = "获取身份和城市对应的企业数量")
    public JSONObject getEnterpriseProvinceInfo(){
        return enterpriseService.getEnterpriseProvinceInfo();
    }
}
