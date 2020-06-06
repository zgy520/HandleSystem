package com.zgy.handle.handleService.controller.staticInfo;

import com.alibaba.fastjson.JSONObject;
import com.zgy.handle.handleService.model.staticInfo.HandleType;
import com.zgy.handle.handleService.service.staticInfo.HandleDailyCountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "static")
@Slf4j
@Api(tags = "大屏统计")
public class HandleDailyController {
    @Autowired
    HandleDailyCountService handleDailyCountService;


    //@Scheduled(cron = "0 0 23 * * ?")
    @Scheduled(cron = "59 58 23 * * ?")
    @RequestMapping(value = "getRegInfo")
    @ApiIgnore
    public void getRegInfo(){
        handleDailyCountService.updateDailyCount();
    }

    @GetMapping(value = "getTotal")
    @ApiOperation(value = "获取月份和总的数量")
    public JSONObject getTotal(HandleType handleType){
        return handleDailyCountService.getTotalByType(handleType);
    }
    @GetMapping(value = "getMonthDays")
    @ApiOperation(value = "获取当前月每天的")
    public JSONObject getMonthDays(HandleType handleType){
        return handleDailyCountService.getMonthDays(handleType);
    }

    @GetMapping(value = "getYearMonths")
    @ApiOperation(value = "获取当年每月的")
    public JSONObject getYearMonths(HandleType handleType){
        return handleDailyCountService.getYearMonths(handleType);
    }
}
