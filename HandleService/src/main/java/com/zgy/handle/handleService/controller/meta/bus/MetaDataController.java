package com.zgy.handle.handleService.controller.meta.bus;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "mdc")
@Slf4j
@Api(tags = "业务数据的接口")
public class MetaDataController {

    @PostMapping(value = "上传业务数据")
    public JSONObject importExcel(HttpServletResponse response, @RequestParam(value = "file",required = true) MultipartFile file,String attachData){
        return null;
    }
}
