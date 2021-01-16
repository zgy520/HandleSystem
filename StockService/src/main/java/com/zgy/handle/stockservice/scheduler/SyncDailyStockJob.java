package com.zgy.handle.stockservice.scheduler;

import com.zgy.handle.stockservice.service.dailytrend.StockDailyTrenderService;
import com.zgy.handle.stockservice.service.move.StockMovingService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.script.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringWriter;

/**
 * @author: a4423
 * @date: 2020/12/27 8:16
 */
@Slf4j
public class SyncDailyStockJob implements Job {
    @Autowired
    private StockMovingService stockMovingService;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        stockMovingService.calStockMoving();
    }
}
