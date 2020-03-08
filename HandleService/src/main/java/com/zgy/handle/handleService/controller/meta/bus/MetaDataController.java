package com.zgy.handle.handleService.controller.meta.bus;

import com.alibaba.fastjson.JSONObject;
import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.handleService.excelTools.ImportType;
import com.zgy.handle.handleService.service.Import.factory.BusinessType;
import com.zgy.handle.handleService.service.meta.bus.BusinessDataUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private BusinessDataUploadService businessDataUploadService;

    @PostMapping(value = "upload")
    @ApiOperation("上传业务数据")
    public JSONObject importExcel(HttpServletResponse response, @RequestParam(value = "file",required = true) MultipartFile file, BusinessType businessType, String attachData){
        JSONObject jsonObject = new JSONObject();
        ImportType importType = new ImportType(businessType.name(),"元数据的导入");
        ResponseCode responseCode = businessDataUploadService.importExcel(response,file,businessType,importType,attachData);
        jsonObject.put("code",20000);
        jsonObject.put("responseCode",response);
        return jsonObject;
    }
}
