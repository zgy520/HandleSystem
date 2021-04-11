package com.zgy.handle.tradingservice.model;

import com.zgy.handle.common.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * @author: a4423
 * @date: 2021/4/6 22:14
 */
@Entity
@Data
@Table(name = "plan")
@NoArgsConstructor
@AllArgsConstructor
public class TradingPlan extends BaseModel {
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
}
