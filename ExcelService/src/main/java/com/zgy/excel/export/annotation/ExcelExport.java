package com.zgy.excel.export.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * excel导出的注解
 * @author a4423
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelExport {
    /**
     * 导出的Excel文件名
     * @return
     */
    String fileName() default  "exportExcel";

    /**
     * 工作簿表单的名称
     * @return
     */
    String sheetName() default  "Sheet1";

    /**
     * 标题名称，如果为空，则无标题
     * @return
     */
    String title() default  "";
}
