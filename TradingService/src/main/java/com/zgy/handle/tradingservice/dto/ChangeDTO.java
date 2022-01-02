package com.zgy.handle.tradingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

/**
 * @author: a4423
 * @date: 2021/4/8 22:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class ChangeDTO extends BaseDTO{
    private String planId;
    /**
     * 跟踪日期
     */
    private LocalDate changeDate;
    /**
     * 1 加仓； -1 减仓
     */
    private byte type;

    /**
     * 原因
     */
    private String reason;
}
