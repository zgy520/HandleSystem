package com.zgy.handle.timetradingservice.model;

import com.zgy.handle.common.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * mou ge shi jian duan nei de fen xi
 * @author: a4423
 * @date: 2021/4/18 18:57
 */
@Entity
@Table(name = "daily_period")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDailyPeriod extends BaseModel {
    private LocalDate curDate;
    //
    private int count;
    private int volume;
    private double money;
    private double zdf;
    private String periodType;
}
