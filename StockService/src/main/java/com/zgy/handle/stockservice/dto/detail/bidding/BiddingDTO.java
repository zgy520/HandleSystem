package com.zgy.handle.stockservice.dto.detail.bidding;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author: a4423
 * @date: 2021/2/17 23:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiddingDTO {
    /**
     * 交易日期
     */
    private LocalDate dealDate;
    /**
     * 竞价数量
     */
    private Integer count;
    /**
     * 涨跌幅
     */
    private Double zdf;
}
