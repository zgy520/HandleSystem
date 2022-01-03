package com.zgy.handle.tradingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

/**
 * @author: a4423
 * @date: 2021/4/8 22:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class TrackingDTO extends BaseDTO {
    private String planId;
    /**
     * 跟踪日期
     */
    private LocalDate trackDate;

    private LocalDate endDate;

    /**
     * 买出价格
     */
    private Double sellPrice;
    /**
     * 卖出价格
     */
    private Double buyPrice;

    /**
     * 差价
     */
    private Double priceDiff;

    /**
     * 提取金额
     */
    private Double extractMoney;

    /**
     * 收益率
     */
    private Double yieldRate;
    /**
     * 交易规则是否发生变更
     * 规则后续应该对其进行维护，为选择项
     */
    private String rule;

    /**
     * 数量
     */
    private Integer count;
}
