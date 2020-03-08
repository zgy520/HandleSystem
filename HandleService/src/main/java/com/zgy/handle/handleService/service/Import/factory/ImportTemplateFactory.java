package com.zgy.handle.handleService.service.Import.factory;


import com.zgy.handle.handleService.excelTools.ExcelBase;
import com.zgy.handle.handleService.excelTools.ExcelFileUtils;
import com.zgy.handle.handleService.excelTools.ImportType;
import com.zgy.handle.handleService.service.Import.securityCheck.EnterpriseInfoImport;
import com.zgy.handle.handleService.service.Import.securityCheck.MetaNodeImport;
import org.apache.commons.lang.StringUtils;

public class ImportTemplateFactory {
    /**
     * 根据模板路径获取对应的实现类
     * @param importType
     * @return
     */
    public static ExcelBase getExcelImpl(BusinessType businessType, ImportType importType, String templatePath){
        if (importType == null)
            return null;
        if (StringUtils.isBlank(templatePath))
            templatePath = ExcelFileUtils.getExcelTemplatePath(importType);
        switch (businessType){
            case META_DATA_BUSINESS:
                return new MetaNodeImport(templatePath);
            case ENTERPISE_INFO:
                return new EnterpriseInfoImport(templatePath);
            default:
                return null;
        }
    }
}
