package com.zgy.handle.userservice.service.excel;


import com.alibaba.fastjson.JSONArray;
import com.zgy.excel.importExcel.ExcelBase;
import com.zgy.excel.importExcel.ImportResult;
import com.zgy.excel.importExcel.ImportType;
import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.common.service.RequestUserService;
import com.zgy.handle.userservice.core.error.ErrorNum;
import com.zgy.handle.userservice.core.exception.BusinessException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;

/**
 * excel上传的处理类
 *
 * @param <T>
 * @author a4423
 */
@Slf4j
public abstract class TemplateUploadService<T> extends RequestUserService {

    /**
     * 业务类型，对应文件名
     * 该值 有各个业务模块自己定义，自由定义，可区分即可
     */
    private String businessType;

    public TemplateUploadService(String businessType) {
        this.businessType = businessType;
    }

    /**
     * 对经过验证的数据进行处理，包括进行关联表的验证、数据的保持等
     *
     * @param jsonArray  已验证通过的数据
     * @param attachData 附加数据
     * @return 返回在处理过程中发现问题的数据
     */
    public abstract JSONArray handleData(JSONArray jsonArray, String attachData);

    /**
     * 获取Excel导入的处理模板
     *
     * @param file 上传的文件
     * @return
     */
    public abstract ExcelBase getExcelBase(File file);

    /**
     * 导入excel的具体实现类
     *
     * @param file 上传的文件
     * @return 处理结果
     */
    @SneakyThrows
    public ResponseCode<String> importExcel(MultipartFile file, String attachData) {
        ResponseCode<String> responseCode = ResponseCode.sucess();
        if (file.isEmpty()) {
            throw new BusinessException(ErrorNum.FILE_UPLOAD_NOT_FILE);
        } else {
            ImportType importType = new ImportType(businessType);
            ExcelBase excelBase = this.getExcelBase(convertMultiPartToFile(file));
            if (excelBase.judgeTemplateEffective()) {
                // 合法模板
                JSONArray jsonArray = excelBase.readExcelData();
                // 解析获取道的jsonArray
                ImportResult importResult = excelBase.getImportResult();
                if (jsonArray.size() > 0) {
                    importResult.addSaveFailArray(handleData(jsonArray, attachData));
                }
                Path errorPath = excelBase.writeErrorDataToExcel(importResult, importType);
                if (errorPath != null && (importResult.getValidateFailArray().size() > 0 || importResult.getSaveFailArray().size() > 0)) {
                    log.info("错误路径为:" + errorPath.toString());
                    log.info("有错误的数据，需要提示导出");
                    responseCode.setSuccess(false);
                    responseCode.setMsg(errorPath.getFileName().toString());
                }
            } else {
                throw new BusinessException(ErrorNum.FILE_UPLOAD_TEMPLATE_ERROR);
            }
        }
        return responseCode;
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
