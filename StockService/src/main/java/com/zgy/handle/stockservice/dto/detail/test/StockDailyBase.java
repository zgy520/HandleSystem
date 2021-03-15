package com.zgy.handle.stockservice.dto.detail.test;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author: a4423
 * @date: 2021/2/28 23:29
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class StockDailyBase {
    private String name;
    private String code;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate curDate;
    /**
     * 成交时间
     */
    private LocalTime curTime;
    private Double price;
    /**
     * 成交量（手）
     */
    private Integer tradingCount;
    /**
     * 类型：买入、卖出
     */
    private Integer type;
    /**
     * 成交结果： 上升、下降、持平
     */
    private Integer result;
}
