package com.zgy.handle.userservice.service.excel;


import com.zgy.excel.importExcel.ExcelBase;
import com.zgy.excel.importExcel.ImportType;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class TemplateDownService {

    public ResponseEntity<Resource> downloadErrorExcel(HttpServletRequest request, BusinessType businessType, String fileName) {
        ImportType importType = new ImportType(businessType.name(), fileName);
        ExcelBase templateExcel = ImportTemplateFactory.getExcelImpl(businessType, importType, (String) null);
        return templateExcel.downloadErrorData(request, importType, fileName);
    }
}
