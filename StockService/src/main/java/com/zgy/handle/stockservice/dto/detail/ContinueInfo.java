package com.zgy.handle.stockservice.dto.detail;

import lombok.Data;

import java.time.LocalTime;

/**
 * 股票持续买卖情况
 * @author: a4423
 * @date: 2021/1/25 22:56
 */
@Data
public class ContinueInfo {
    /**
     * 开始时间
     */
    private LocalTime startTime;
    /**
     * 结束时间
     */
    private LocalTime endTime;
    /**
     * 交易次数
     */
    private Integer count;
    /**
     * 平均价格
     */
    private Double averagePrice;
    /**
     * 总手数
     */
    private Integer total;
    /**
     * 交易类型：买入、卖出
     */
    private String type;
}
