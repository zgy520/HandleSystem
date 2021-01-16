package com.zgy.handle.stockservice.dto.detail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 基础详情信息
 * @author: a4423
 * @date: 2021/1/3 19:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDetailDTO {
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

}
