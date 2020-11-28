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
 * @date: 2020/10/20 22:59
 * 股票均线
 */
@Entity(name = "moving")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockMoving extends BaseModel {
    private String name;
    private String code;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate curDate;
    /**
     * 当日价格
     */
    private Double price;
    /**
     * 3日均线
     */
    private Double price3;
    private Double price5;
    private Double price7;
    private Double price10;
    private Double price15;
    private Double price20;
    private Double price30;
    private Double price45;
    private Double price60;
    private Double price75;
    private Double price100;
}
