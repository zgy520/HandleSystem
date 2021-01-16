package com.zgy.handle.stockservice.model.record;

import com.zgy.handle.common.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * 日记
 * @author: a4423
 * @date: 2020/12/26 12:18
 */
@Entity
@Table(name = "record")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockRecord extends BaseModel {
    private String name;
    private String code;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate curDate;
    /**
     * 涨跌幅
     */
    private Double zdf;
    /**
     * 大盘涨跌幅
     */
    private Double dpZdf;
    /**
     * 整体走势
     */
    private String trend;
    /**
     * 记录
     */
    private String record;

    /**
     * 上一交易日的预测
     */
    private String prePrediction;

    /**
     * 匹配度
     */
    private Integer matchRate;

    /**
     * 匹配分析
     */
    private String matchAnalysis;

    /**
     * 预测
     */
    private String prediction;
    /**
     * 预测缘由
     */
    private String predictionReason;

}
