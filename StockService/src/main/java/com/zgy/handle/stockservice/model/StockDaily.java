package com.zgy.handle.stockservice.model;

import com.zgy.handle.common.model.BaseModel;
import com.zgy.handle.stockservice.enums.CloseStatus;
import com.zgy.handle.stockservice.enums.OpeningStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

/**
 * @author: a4423
 * @date: 2020/10/18 23:07
 */
@Entity(name = "daily")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDaily extends BaseModel {
    private String name;
    private String code;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate curDate;
    private Double startPrice;
    private Double endPrice;
    private Double highPrice;
    private Double minPrice;
    /**
     * 换手率
     */
    private Double turnOverRate;
    /**
     * 涨跌幅
     */
    private Double zdf;
    /**
     * 成交量
     */
    private Integer tradingVolume;


    /**
     * 大盘涨幅
     */
    private Double szZdf;
    /**
     * 大盘收盘价
     */
    private Double zdEndPrice;


}
