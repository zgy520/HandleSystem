package com.zgy.handle.stockservice.model;

import com.zgy.handle.common.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import java.time.LocalDate;

/**
 * @author: a4423
 * @date: 2020/10/18 23:34
 * 变化趋势
 */
@Entity(name = "daily_trend")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDialyTrend extends BaseModel {
    private String name;
    private String code;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate curDate;
    /**
     * 开盘走势
     */
    private String openTrend;
    /**
     * 开盘十五分钟走势
     */
    private String open15Trend;
    /**
     * 开盘1小时走势
     */
    private String open60Trend;
    /**
     * 上午走势
     */
    private String amTrend;
    /**
     * 中午开盘半小时走势
     */
    private String noon30Trend;

    /**
     * 整体走势
     */
    private String trend;
}
