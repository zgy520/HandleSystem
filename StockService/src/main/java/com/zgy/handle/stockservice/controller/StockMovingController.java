package com.zgy.handle.stockservice.controller;

import com.zgy.handle.stockservice.service.move.StockMovingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: a4423
 * @date: 2020/12/20 13:27
 */
@RestController
@RequestMapping(value = "stock/movie")
public class StockMovingController {
    @Autowired
    private StockMovingService stockMovingService;

    @RequestMapping(value = "initMovie",method = RequestMethod.POST)
    public void initMovie(){
        stockMovingService.initStockMoving();
    }
}
