package com.zgy.handle.stockservice.service.record;

import com.zgy.handle.stockservice.model.record.StockRecord;

import java.time.LocalDate;

/**
 * @author: a4423
 * @date: 2020/12/26 14:08
 */
public interface StockRecordService {
    /**
     * 添加日志
     * @param stockRecord
     */
    void addRecord(StockRecord stockRecord);

    /**
     * 根据记录日期获取记录详情
     * @param code 代码
     * @param recordDate 记录日期
     * @return 记录详情
     */
    StockRecord getDetail(String code, LocalDate recordDate);
}
