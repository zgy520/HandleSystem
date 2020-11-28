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
 * @date: 2020/10/20 22:44
 * 近期变化趋势
 */
@Entity(name = "trend")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockTrend extends BaseModel {
    private String name;
    private String code;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate curDate;
    /**
     * 近期最高价
     */
    private Double highestPrice;
    /**
     * 近期最低价
     */
    private Double minPrice;
    /**
     * 持续震荡天数
     */
    private Integer volatileDays;
    /**
     * 成交量趋势
     */
    private String volumeTrend;
    /**
     * 成交量
     */
    private Integer volume;

}
