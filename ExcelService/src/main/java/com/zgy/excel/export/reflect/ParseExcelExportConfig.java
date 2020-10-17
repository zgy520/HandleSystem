package com.zgy.excel.export.reflect;

import com.zgy.excel.export.annotation.ExcelExport;
import com.zgy.excel.export.annotation.ExcelProperty;
import com.zgy.excel.export.config.ExcelExportConfig;
import com.zgy.excel.export.model.ExportFieldInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author: a4423
 * @date: 2020/10/17 20:52
 */
@Slf4j
public class ParseExcelExportConfig {
    /**
     * 基于Excel注解相关的熟悉解析对象，并将其值置于对应的对象中
     * @param excelExportConfig Excel导出的配置类
     * @param tCls 对象类
     */
    public static void fillExcelExportConfig(ExcelExportConfig excelExportConfig, Class tCls) {
        Field[] fields = tCls.getDeclaredFields();
        List<ExportFieldInfo> exportFieldInfoList = new ArrayList<>();
        for (Field field : fields) {
            ExcelProperty excelProperty = field.getAnnotation(ExcelProperty.class);
            if (excelProperty == null) continue;
            ExportFieldInfo exportFieldInfo = new ExportFieldInfo(field.getName(), excelProperty.title(), excelProperty.serial());
            exportFieldInfoList.add(exportFieldInfo);
        }
        exportFieldInfoList.sort(Comparator.comparing(ExportFieldInfo::getSerial));
        //exportFieldInfoList.forEach(exportFieldInfo -> log.info("字段为:{},标题名为:{},序号为:{}", exportFieldInfo.getCode(), exportFieldInfo.getTitle(), exportFieldInfo.getSerial()));
        int size = exportFieldInfoList.size();
        String[] titles = new String[size];
        String[] codes = new String[size];
        for (int i = 0; i < size; i++) {
            titles[i] = exportFieldInfoList.get(i).getTitle();
            codes[i] = exportFieldInfoList.get(i).getCode();
        }
        excelExportConfig.setTitles(titles);
        excelExportConfig.setFields(codes);

        ExcelExport excelExport = (ExcelExport) tCls.getAnnotation(ExcelExport.class);
        excelExportConfig.setExcelFileName(excelExport.fileName());
        excelExportConfig.setSheetName(excelExport.sheetName());
        if (StringUtils.isNotBlank(excelExport.title())){
            excelExportConfig.setShowHeader(true);
            excelExportConfig.setHeader(excelExport.title());
        }else {
            excelExportConfig.setShowHeader(false);
        }
    }
}
