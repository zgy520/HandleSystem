package com.zgy.handle.timetradingservice.model;

import com.zgy.handle.common.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalTime;

/**
 * @author: a4423
 * @date: 2021/4/14 23:17
 */
@Entity
@Table(name = "trading_base_count")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradingCount extends BaseModel {
    private String code;
    private String name;
    private String date;
    /**
     * 总交易次数
     */
    private Integer tradingCount;
    /**
     * 总交易量
     */
    private Integer tradingVolume;
    /**
     * 买入次数
     */
    private Long buyCount;
    /**
     * 买入量
     */
    private Integer buyVolume;
    /**
     * 卖出次数
     */
    private Long saleCount;
    /**
     * 卖出量
     */
    private Integer saleVolume;
    /**
     * 买入金额
     */
    private Double buyMoney;
    /**
     * 卖出金额
     */
    private Double saleMoney;
    /**
     * 净流入金额
     */
    private Double totalMoney;
    /**
     * 最高价
     */
    private Double maxPrice;
    /**
     * 最高价发生的时间
     */
    private LocalTime maxTime;
    /**
     * 最低价
     */
    private Double minPrice;
    /**
     * 最低价发生的时间
     */
    private LocalTime minTime;
}
