package com.zgy.handle.stockservice.dto.cal;

import lombok.Data;

import java.util.List;

/**
 * @author: a4423
 * @date: 2020/12/20 18:22
 */
@Data
public class SummaryWeekDTO {
    private String name;
    private String code;
    /**
     * 总数量
     */
    private Integer totalSize;
    /**
     * 上涨概率
     */
    private String upRate;
    /**
     * 下降概率
     */
    private String downRate;
    /**
     * 上升数量
     */
    private Long upSize;
    /**
     * 下降数量
     */
    private Long downSize;
    /**
     * 每天评价涨跌幅
     */
    private Double average;
    private List<StockWeekDTO> stockWeekDTOList;
}
