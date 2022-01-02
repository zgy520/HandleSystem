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
 * @date: 2021/4/6 22:32
 */
@Entity
@Data
@Table(name = "track")
@NoArgsConstructor
@AllArgsConstructor
public class Tracking extends BaseModel {
    private Long planId;
    /**
     * 跟踪日期
     */
    private LocalDate trackDate;

    /**
     * 买出价格
     */
    private Double sellPrice;
    /**
     * 买入价格
     */
    private Double buyPrice;


    /**
     * 差价
     */
    private Double priceDiff;


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
