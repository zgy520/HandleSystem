package com.zgy.handle.stockservice.dto.detail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 交易类型的信息
 * @author: a4423
 * @date: 2021/1/6 21:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionTypeDTO {

    /**
     * 代码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 交易日期
     */
    private String tradingDate;

    /**
     * 单子类型：大单、中单和小单
     */
    private String type;

    /**
     * 交易次数
     */
    private Long tradingCount;

    /**
     * 交易量
     */
    private Integer tradingVolume;

    /**
     * 成交金额
     */
    private Integer tradingMoney;

    /**
     * 买入次数
     */
    private Long buyCount;

    /**
     * 买入量
     */
    private Integer buyVolume;

    /**
     * 卖出数量
     */
    private Long saleCount;

    /**
     * 卖出量
     */
    private Integer saleVolume;
}
