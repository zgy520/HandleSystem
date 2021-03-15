package com.zgy.handle.stockservice.dto.detail.test;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author: a4423
 * @date: 2021/3/1 22:40
 */
public interface StockDailyInterfaceBase {
    String getName();
    String getCode();
    LocalDate getCurDate();
    LocalTime getCurTime();
    Double getPrice();
    Integer getTradingCount();
    Integer getType();
    Integer getResult();
}
