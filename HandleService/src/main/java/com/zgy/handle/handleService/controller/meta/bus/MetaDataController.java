package com.zgy.handle.handleService.controller.meta.bus;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.handleService.excelTools.ImportType;
import com.zgy.handle.handleService.service.Import.factory.BusinessType;
import com.zgy.handle.handleService.service.meta.bus.BusPrimaryService;
import com.zgy.handle.handleService.service.meta.bus.BusinessDataUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "mdc")
@Slf4j
@Api(tags = "业务数据的接口")
public class MetaDataController {
    @Autowired
    private BusinessDataUploadService businessDataUploadService;
    @Autowired
    private BusPrimaryService busPrimaryService;

    @PostMapping(value = "upload")
    @ApiOperation("上传业务数据")
    public ResponseCode importExcel(HttpServletResponse response, @RequestParam(value = "file",required = true) MultipartFile file, BusinessType businessType, String attachData){
        JSONObject jsonObject = new JSONObject();
        ImportType importType = new ImportType(businessType.name(),"元数据的导入");
        ResponseCode responseCode = businessDataUploadService.importExcel(response,file,businessType,importType,attachData);
        jsonObject.put("code",20000);
        jsonObject.put("responseCode",response);
        return responseCode;
    }
    @GetMapping(value = "getBusData/{metaHeaderId}")
    public ResponseCode<JSONArray> getBusData(Long metaHeaderId){
        ResponseCode<JSONArray> responseCode = busPrimaryService.getBusData(metaHeaderId);
        return responseCode;
    }
}
