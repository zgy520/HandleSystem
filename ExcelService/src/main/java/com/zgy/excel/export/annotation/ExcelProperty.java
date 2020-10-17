package com.zgy.excel.export.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Excel导出的注解属性
 * @author a4423
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelProperty {
    /**
     * 字段名称
     * @return
     */
    String title() default "";

    /**
     * 在Excel中的顺序
     * @return
     */
    int serial() default 0;



}
