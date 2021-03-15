package com.zgy.handle.stockservice.service.util;

import java.time.LocalDate;
import java.util.List;

/**
 * 辅助服务类
 * @author: a4423
 * @date: 2021/2/17 22:31
 */
public interface StockUtilService {
    /**
     * 获取所有的交易日,并降序
     * @param code 股票代码
     * @return
     */
    List<LocalDate> getDealDateAndSortDesc(String code);
}
