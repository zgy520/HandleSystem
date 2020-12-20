package com.zgy.handle.stockservice.dao;

import com.zgy.handle.stockservice.model.StockDialyTrend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * @author: a4423
 * @date: 2020/12/20 10:33
 */
@Repository
public interface StockDailyTrendRepository extends JpaRepository<StockDialyTrend,Long> {
    /**
     * 根据日期和代码获取当前的走势信息
     * @param curDate 日期
     * @param code 代码
     * @return 走势信息
     */
    StockDialyTrend findByCurDateAndCode(LocalDate curDate,String code);
}
