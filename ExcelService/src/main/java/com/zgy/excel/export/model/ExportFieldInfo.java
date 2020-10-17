package com.zgy.excel.export.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: a4423
 * @date: 2020/10/17 21:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExportFieldInfo {
    /**
     * 编码
     */
    private String code;
    /**
     * 标题
     */
    private String title;
    /**
     * 序号
     */
    private Integer serial;
}
