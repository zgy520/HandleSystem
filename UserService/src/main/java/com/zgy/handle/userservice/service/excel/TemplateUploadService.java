package com.zgy.handle.userservice.service.excel;


import com.alibaba.fastjson.JSONArray;
import com.zgy.excel.importExcel.ExcelBase;
import com.zgy.excel.importExcel.ExcelFileUtils;
import com.zgy.excel.importExcel.ImportResult;
import com.zgy.excel.importExcel.ImportType;
import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.common.service.RequestUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

/**
 * excel上传的处理类
 *
 * @param <T>
 * @author a4423
 */
@Slf4j
public abstract class TemplateUploadService<T> extends RequestUserService {

    /**
     * 对经过验证的数据进行处理，包括进行关联表的验证、数据的保持等
     *
     * @param jsonArray  已验证通过的数据
     * @param attachData 附加数据
     * @return 返回在处理过程中发现问题的数据
     */
    public abstract JSONArray handleData(JSONArray jsonArray, String attachData);

    /**
     * 导入excel的具体实现类
     *
     * @param file 上传的文件
     * @param businessType 业务类型
     * @return 处理结果
     */
    public ResponseCode<String> importExcel(MultipartFile file, BusinessType businessType, String attachData) {
        ResponseCode<String> responseCode = ResponseCode.sucess();
        if (file.isEmpty()) {
            responseCode.setSuccess(false);
            responseCode.setMsg("请先上传文件!");
        } else {
            ImportType importType = new ImportType(businessType.name(), businessType.name());
            String excelPath = ExcelFileUtils.getExcelPath(file);
            ExcelBase excelBase = ImportTemplateFactory.getExcelImpl(businessType, importType, excelPath);
            if (excelBase.judgeTemplateEffective()) {
                // 合法模板
                JSONArray jsonArray = excelBase.readExcelData();
                // 解析获取道的jsonArray
                ImportResult importResult = excelBase.getImportResult();
                if (jsonArray.size() > 0) {
                    importResult.addSaveFailArray(handleData(jsonArray, attachData));
                }
                Path errorPath = excelBase.writeErrorDataToExcel(importResult, importType);
                log.info("错误路径为:" + errorPath.toString());
                if (importResult.getValidateFailArray().size() > 0 || importResult.getSaveFailArray().size() > 0) {
                    log.info("有错误的数据，需要提示导出");
                    responseCode.setSuccess(false);
                    responseCode.setMsg(errorPath.getFileName().toString());
                }
            } else {
                responseCode.setSuccess(false);
                responseCode.setMsg("请传入正确的模板");
            }
        }
        return responseCode;
    }
}
