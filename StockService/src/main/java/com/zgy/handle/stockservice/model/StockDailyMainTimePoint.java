package com.zgy.handle.stockservice.model;

import com.zgy.handle.common.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 当前升降的主要时间点
 * @author: a4423
 * @date: 2020/10/20 22:30
 */
@Entity(name = "daily_main_time_point")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDailyMainTimePoint extends BaseModel {
    private String name;
    private String code;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate curDate;
    /**
     * 变更时间
     */
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime changeTime;
    /**
     * 变更类型：上升、下降
     */
    private String type;

}
