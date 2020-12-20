package com.zgy.handle.stockservice.controller;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.stockservice.model.StockDialyTrend;
import com.zgy.handle.stockservice.service.dailytrend.StockDailyTrenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: a4423
 * @date: 2020/12/20 10:32
 */
@RestController
@RequestMapping(value = "/stock/daily/trend")
public class StockDailyTrendController {
    private StockDailyTrenderService stockDailyTrenderService;
    @Autowired
    public StockDailyTrendController(StockDailyTrenderService stockDailyTrenderService){
        this.stockDailyTrenderService = stockDailyTrenderService;
    }

    /**
     * 液压的当前走势信息
     * @param stockDialyTrend
     * @return
     */
    @PostMapping(value = "ymTrend")
    private ResponseCode<String> addStockDailyTrend(StockDialyTrend stockDialyTrend){
        stockDialyTrend.setName("恒立液压");
        stockDialyTrend.setCode("601100");
        stockDialyTrend.setCreator("WX");
        stockDailyTrenderService.addStockDailyTrend(stockDialyTrend);
        return ResponseCode.sucess();
    }
}
