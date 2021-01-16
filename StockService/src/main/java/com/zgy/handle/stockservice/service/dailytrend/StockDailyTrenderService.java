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

    /**
     * 获取某天的趋势记录
     * @param code 股票代码
     * @param trendDate 记录日期
     * @return 记录详情
     */
    StockDialyTrend getDetails(String code,String trendDate);
}
