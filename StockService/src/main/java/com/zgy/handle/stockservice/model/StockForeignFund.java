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
 * 北上资金流入情况
 * @author: a4423
 * @date: 2020/11/5 22:21
 */
@Entity
@Table(name = "foreign_fund")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockForeignFund extends BaseModel {
    private String name;
    private String code;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate curDate;

    /**
     * 总流入
     */
    private Double inTotal;
    /**
     * 总流出
     */
    private Double outTotal;
    /**
     * 当日净额
     */
    private Double finalTotal;

    /**
     * 当前流入趋势
     */
    private String trend;


}
