package com.zgy.handle.stockservice.controller;

import com.zgy.handle.stockservice.service.daily.StockDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: a4423
 * @date: 2021/2/19 23:15
 */
@RestController(value = "stock/daily")
public class StockDailyController {
    @Autowired
    private StockDailyService stockDailyService;

    @PostMapping(value = "fillPrePrice")
    private void fillPrePrice(){
        stockDailyService.updatePreClosePrice();
    }
}
