package com.zgy.handle.tradingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: a4423
 * @date: 2021/4/8 22:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class SummaryDTO extends BaseDTO {
    private String planId;
    /**
     * 总的收益率
     */
    private Double yieldRate;
    /**
     * 总结
     */
    private String summary;
}
