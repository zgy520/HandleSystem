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
 * @date: 2021/4/6 22:41
 * 加减仓情况
 */
@Entity
@Data
@Table(name = "change")
@NoArgsConstructor
@AllArgsConstructor
public class Change extends BaseModel {
    private Long planId;
    /**
     * 跟踪日期
     */
    private LocalDate changeDate;
    /**
     * 1 加仓； -1 减仓
     */
    private byte type;

    /**
     * 原因
     */
    private String reason;

}
