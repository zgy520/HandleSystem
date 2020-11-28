package com.zgy.handle.stockservice.model;

import com.zgy.handle.common.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import java.time.LocalDate;

/**
 * 当日股票价格变动情况
 * @author: a4423
 * @date: 2020/10/20 22:37
 */
@Entity(name = "daily_price_change")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDailyPriceChange extends BaseModel {
    private String name;
    private String code;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate curDate;

    /**
     * 交叉次数
     */
    private Integer crossNumber;

    /**
     * 回调次数
     */
    private Integer callbackNumber;

    /**
     * 上升次数
     */
    private Integer upNumber;

    /**
     * 下降次数
     */
    private Integer downNumber;

}
