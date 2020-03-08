package com.zgy.handle.handleService.service.Import;


import com.alibaba.fastjson.JSONArray;
import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.handleService.excelTools.ExcelBase;
import com.zgy.handle.handleService.excelTools.ExcelFileUtils;
import com.zgy.handle.handleService.excelTools.ImportResult;
import com.zgy.handle.handleService.excelTools.ImportType;
import com.zgy.handle.handleService.service.Import.factory.BusinessType;
import com.zgy.handle.handleService.service.Import.factory.ImportTemplateFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * excel上传的处理类
 * @param <T>
 */
@Slf4j
public abstract class TemplateUploadService<T> {

    public abstract JSONArray handleData(JSONArray jsonArray, String attachData);

    /**
     * 导入excel的具体实现类
     * @param response
     * @param file
     * @param importType
     * @param businessType
     * @return
     */
    public ResponseCode importExcel(HttpServletResponse response, MultipartFile file, BusinessType businessType, ImportType importType, String attachData){
        ResponseCode defaultErrorCode = ResponseCode.sucess();
        if (file.isEmpty()){
            defaultErrorCode.setSuccess(false);
            defaultErrorCode.setMsg("请先上传文件!");
        }else {
            String excelPath = ExcelFileUtils.getExcelPath(file);
            ExcelBase excelBase = ImportTemplateFactory.getExcelImpl(businessType,importType,excelPath);
            if (excelBase.judgeTemplateEffective()){
                // 合法模板
                JSONArray jsonArray = excelBase.readExcelData();
                // 解析获取道的jsonArray
                ImportResult importResult = excelBase.getImportResult();
                if (jsonArray.size() > 0){
                   importResult.addSaveFailArray(handleData(jsonArray,attachData));
                }
                log.info("获取到的业务数据的id为:" + importResult.getSaveFailArray().toJSONString());
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < importResult.getSaveFailArray().size(); i++){
                    stringBuilder.append(importResult.getSaveFailArray().getJSONObject(i).getString("primary"+i));
                    stringBuilder.append(",");
                }
                if (stringBuilder.length() > 0){
                    String substring = stringBuilder.substring(0,stringBuilder.length() - 1);
                    defaultErrorCode.setMsg(substring);
                }


                //LOGGER.info("对于保存失败的情况如下：验证失败的数量为" + importResult.getValidateFailArray().size() + "个;保存失败的个数为:" + importResult.getSaveFailArray().size() + "个");
                /*LOGGER.info(importResult.getValidateFailArray().toJSONString());
                LOGGER.info(importResult.getSaveFailArray().toJSONString());*/
                /*Path errorPath = excelBase.writeErrorDataToExcel(importResult,importType);
                log.info("错误路径为:" + errorPath.toString());
                if (importResult.getValidateFailArray().size() > 0 || importResult.getSaveFailArray().size() > 0){
                    log.info("有错误的数据，需要提示导出");
                    defaultErrorCode.setErrorCode(ErrorCode.FAIL);
                    defaultErrorCode.setMsg(errorPath.getFileName().toString());
                }*/
            }else {
                defaultErrorCode.setSuccess(false);
                defaultErrorCode.setMsg("请传入正确的模板");
            }
        }
        return defaultErrorCode;
    }
}
