package com.zgy.handle.stockservice.model.detail;

import com.zgy.handle.common.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author: a4423
 * @date: 2020/12/29 23:17
 */
@Entity
@Table(name = "daily_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDailyDetail extends BaseModel {
    private String name;
    private String code;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate curDate;
    /**
     * 成交时间
     */
    private LocalTime curTime;
    private Double price;
    /**
     * 成交量（手）
     */
    private Integer tradingCount;
    /**
     * 类型：买入、卖出
     */
    private Integer type;
    /**
     * 成交结果： 上升、下降、持平
     */
    private Integer result;
}
