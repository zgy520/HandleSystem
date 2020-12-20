package com.zgy.handle.stockservice.service.dailytrend;

import com.zgy.handle.stockservice.model.StockDialyTrend;

/**
 * @author: a4423
 * @date: 2020/12/20 10:34
 */
public interface StockDailyTrenderService {
    /**
     * 从微信小程序添加每日的趋势信息
     * @param stockDialyTrend 趋势信息
     */
    void addStockDailyTrend(StockDialyTrend stockDialyTrend);
}
