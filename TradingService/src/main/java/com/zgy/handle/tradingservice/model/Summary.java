package com.zgy.handle.tradingservice.model;

import com.zgy.handle.common.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author: a4423
 * @date: 2021/4/7 22:59
 */
@Entity
@Data
@Table(name = "summary")
@NoArgsConstructor
@AllArgsConstructor
public class Summary extends BaseModel {
    private Long planId;
    /**
     * 总的收益率
     */
    private Double yieldRate;
    /**
     * 总结
     */
    private String summary;
    /**
     * 此处应该有个复盘，即复盘这个的交易过程
     */

}
