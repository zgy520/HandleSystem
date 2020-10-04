package com.zgy.handle.userservice.service.excel;


import com.zgy.excel.importExcel.ExcelBase;
import com.zgy.excel.importExcel.ExcelFileUtils;
import com.zgy.excel.importExcel.ImportType;
import com.zgy.handle.userservice.service.authority.role.excel.RoleImportImpl;
import org.apache.commons.lang3.StringUtils;

/**
 * @author a4423
 * @date 2020-10-04 15:39
 */
public class ImportTemplateFactory {
    /**
     * 根据模板路径获取对应的实现类
     *
     * @param importType
     * @return
     */
    public static ExcelBase getExcelImpl(BusinessType businessType, ImportType importType, String templatePath) {
        if (importType == null) {
            return null;
        }
        if (StringUtils.isBlank(templatePath)) {
            templatePath = ExcelFileUtils.getExcelTemplatePath(importType);
        }
        switch (businessType) {
            case ROLE:
                return new RoleImportImpl(templatePath);
            default:
                return null;
        }
    }
}
