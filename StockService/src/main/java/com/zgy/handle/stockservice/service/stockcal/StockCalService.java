package com.zgy.handle.stockservice.service.stockcal;

import com.zgy.handle.stockservice.dto.cal.SummaryWeekDTO;

import java.time.LocalDate;

/**
 * 计算每周一到周五的升降比例以及对应的涨跌幅
 * @author: a4423
 * @date: 2020/12/20 16:56
 */
public interface StockCalService {
    /**
     * 周一至周五的涨跌情况及概率
     */
    SummaryWeekDTO calDayInfo(LocalDate startDate, LocalDate endDate,String code);
}
