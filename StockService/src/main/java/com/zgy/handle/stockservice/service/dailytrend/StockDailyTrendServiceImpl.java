package com.zgy.handle.stockservice.service.dailytrend;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.zgy.handle.stockservice.dao.StockDailyTrendRepository;
import com.zgy.handle.stockservice.model.StockDialyTrend;
import com.zgy.handle.stockservice.util.SpingUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: a4423
 * @date: 2020/12/20 10:34
 */
@Service
@Slf4j
public class StockDailyTrendServiceImpl implements StockDailyTrenderService{
    private StockDailyTrendRepository stockDailyTrendRepository;
    @Autowired
    public StockDailyTrendServiceImpl(StockDailyTrendRepository stockDailyTrendRepository){
        this.stockDailyTrendRepository = stockDailyTrendRepository;
    }
    @Override
    public void addStockDailyTrend(StockDialyTrend stockDialyTrend) {
        log.info("获取到的当前趋势信息为:" + stockDialyTrend.toString());
        StockDialyTrend oldTrend = stockDailyTrendRepository.findByCurDateAndCode(stockDialyTrend.getCurDate(),stockDialyTrend.getCode());
        if (oldTrend == null){
            // 不存在，则新增
            stockDailyTrendRepository.save(stockDialyTrend);
        }else {
            // 已经存在，则将非空数据进行赋值
            BeanUtil.copyProperties(stockDialyTrend, oldTrend, SpingUtils.getNullPropertyNames(stockDialyTrend));
            stockDailyTrendRepository.save(oldTrend);
        }
    }
}
