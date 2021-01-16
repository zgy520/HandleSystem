package com.zgy.handle.stockservice.service.detail;

import com.zgy.handle.stockservice.dto.detail.BaseDetailDTO;
import com.zgy.handle.stockservice.dto.detail.TransactionTypeDTO;

import java.time.LocalDate;
import java.util.List;

/**
 * @author: a4423
 * @date: 2021/1/2 10:37
 */
public interface StockDailyDetailService {
    /**
     * 基本信息
     */
    List<BaseDetailDTO> base(String code, LocalDate localDate);

    /**
     * 交易类型信息
     * @param code 代码
     * @param localDate 交易日期
     * @return
     */
    List<TransactionTypeDTO> transactionInfo(String code,LocalDate localDate);


}
