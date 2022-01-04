package com.zgy.handle.tradingservice.model;

import com.zgy.handle.common.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * @author: a4423
 * @date: 2022/1/4 21:47
 */
@Entity
@Data
@Table(name = "cost_price")
@NoArgsConstructor
@AllArgsConstructor
public class CostPrice extends BaseModel {
    private String code;
    private LocalDate recordDate;
    /**
     * 成本价
     */
    private Double price;
    /**
     * 数量
     */
    private Integer count;
}
