package com.zgy.handle.stockservice.service.daily;

/**
 * @author: a4423
 * @date: 2021/2/19 23:08
 */
public interface StockDailyService {
    /**
     * 更新前一交易日的价格
     */
    void updatePreClosePrice();
}
