package com.zgy.handle.timetradingservice.model;

import com.zgy.handle.common.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * @author: a4423
 * @date: 2021/4/18 18:31
 */
@Entity
@Table(name = "daily")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDaily extends BaseModel {
    private double openPrice;
    private double maxPrice;
    private double minPrice;
    private double endPrice;
    private double volume;
    private double money;
    private double turnOver;
    private double count;
    private LocalDate curDate;
    private double zf;
    private double zdf;
}
