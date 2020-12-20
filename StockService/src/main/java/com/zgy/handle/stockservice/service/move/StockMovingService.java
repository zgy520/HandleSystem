package com.zgy.handle.stockservice.service.move;

/**
 * 均线计算和查询
 * @author: a4423
 * @date: 2020/12/20 12:58
 */
public interface StockMovingService {
    /**
     * 均线初始化
     */
    void initStockMoving();
    /**
     * 计算均线
     */
    void calStockMoving();
}
