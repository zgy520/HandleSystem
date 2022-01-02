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
public class TradingPlanDTO extends BaseDTO {
    private String code;
    private String name;
    /**
     * 开始时间
     */
    private LocalDate startDate;
    /**
     * 计划结束时间
     */
    private LocalDate planEndDate;
    /**
     * 实际结束时间
     */
    private LocalDate endDate;
    /**
     * 成交价格
     */
    private Double transactionPrice;
    /**
     * 计划收益
     */
    private Double planIncome;
    /**
     * 止损点
     */
    private Double lossLimit;
    /**
     * 止盈点
     */
    private Double checkFullPoint;
    /**
     * 开仓原因
     */
    private String reason;
    /**
     * 交易规则
     */
    private String rule;

    /**
     * 成本价
     */
    private Double costPrice;

    /**
     * 已转出金额
     */
    private Double transferMoney;
}
