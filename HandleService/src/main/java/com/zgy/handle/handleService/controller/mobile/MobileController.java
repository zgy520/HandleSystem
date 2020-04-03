package com.zgy.handle.handleService.controller.mobile;

import com.alibaba.fastjson.JSONArray;
import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.handleService.controller.mobile.wx.WeixinToken;
import com.zgy.handle.handleService.model.meta.bus.BusPrimary;
import com.zgy.handle.handleService.model.meta.simulate.WLData;
import com.zgy.handle.handleService.model.meta.simulate.XSData;
import com.zgy.handle.handleService.service.meta.bus.BusPrimaryService;
import com.zgy.handle.handleService.service.meta.structure.MetaHeaderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping(value = "mobile")
@RestController
@Slf4j
@Api(tags = "移动端接口",value = "大部分分接口在相应的业务接口中")
public class MobileController {

    @Autowired
    private BusPrimaryService busPrimaryService;
    @Autowired
    private MetaHeaderService metaHeaderService;

    @GetMapping(value = "getWXInfo")
    public Map<String,String> getWXInfo(String url){
        Map<String,String> map = Sign.sign(WeixinToken.jsapi_ticket,url);
        return map;
    }

    @GetMapping(value = "getAlisa")
    @ResponseBody
    public JSONArray getAlisa(){
        return metaHeaderService.getHandleCodeAlisa();
    }

    @PostMapping(value = "saveWLData")
    @ApiOperation(value = "保存物流数据")
    public ResponseCode<BusPrimary> saveWLData(WLData wlData){
        ResponseCode<BusPrimary> responseCode = busPrimaryService.saveWLData(wlData);
        return responseCode;
    }

    @PostMapping(value = "saveXSData")
    @ApiOperation(value = "保存销售数据")
    public ResponseCode<BusPrimary> saveXSData(XSData xsData){
        ResponseCode<BusPrimary> responseCode = busPrimaryService.saveXSData(xsData);
        return responseCode;
    }
    @RequestMapping(value = "updateWlRelate")
    @ApiOperation(value = "关联单品数据")
    public ResponseCode updateWlRelate(Long busId,String ids){
        return busPrimaryService.updateDPRelate(busId,ids);
    }
}
