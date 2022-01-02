package com.zgy.handle.tradingservice.model;

import com.zgy.handle.common.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * 交易记录
 * @author: a4423
 * @date: 2022/1/2 18:14
 */
@Entity
@Data
@Table(name = "record")
@NoArgsConstructor
@AllArgsConstructor
public class TradingRecord extends BaseModel {
    private String name;
    private String code;
    private LocalDate recordDate;

    private Double buyPrice;
    private Double costPrice;


}
