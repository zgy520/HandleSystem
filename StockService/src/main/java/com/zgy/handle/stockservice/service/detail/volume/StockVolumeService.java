package com.zgy.handle.stockservice.service.detail.volume;

import java.time.LocalDate;

/**
 * @author: a4423
 * @date: 2021/3/1 23:03
 */
public interface StockVolumeService {
    /**
     * 前15分钟的成交量
     * @param curDate 日期
     * @param code 代码
     */
    void pre15Volume(LocalDate curDate, String code);
}
