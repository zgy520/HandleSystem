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
 * @date: 2020/10/20 22:56
 * 换手率
 */
@Entity(name = "turn_over_rate")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockTurnOverRate extends BaseModel {
    private String name;
    private String code;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate curDate;
    /**
     * 当天换手率
     */
    private Double dailyRate;
    /**
     * 三天换手率
     */
    private Double daily3Rate;
    /**
     * 5天换手率
     */
    private Double daily5Rate;
    /**
     * 7天换手率
     */
    private Double daily7Rate;
    /**
     * 10天换手率
     */
    private Double daily10Rate;
    /**
     * 15天换手率
     */
    private Double daily15Rate;
    /**
     * 20天换手率
     */
    private Double daily20Rate;
    /**
     * 30天换手率
     */
    private Double daily30Rate;
}
