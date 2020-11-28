package com.zgy.handle.stockservice.model;

import com.zgy.handle.common.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * 每日竞价
 * @author: a4423
 * @date: 2020/10/26 22:59
 */
@Entity
@Table(name = "daily_bidding")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockBidding extends BaseModel {
    private String name;
    private String code;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate curDate;
    /**
     * 上升次数
     */
    private Integer upCount;
    /**
     * 下降次数
     */
    private Integer downCount;
    /**
     * 竞价最高价
     */
    private Double highestPrice;
    /**
     * 竞价最低价
     */
    private Double minPrice;
    /**
     * 红盘数量
     */
    private Integer redCount;
    /**
     * 绿盘数量
     */
    private Integer greenCount;
    /**
     * 平盘数量
     */
    private Integer grayCount;
    /**
     * 竞价数量
     */
    private Integer biddignCount;
}
