package com.zgy.handle.stockservice.model;

import com.zgy.handle.common.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author: a4423
 * @date: 2020/10/18 23:04
 */
@Entity
@Table(name = "stock")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock extends BaseModel {
    private String name;
    private String code;
    /**
     *
     * 类型： 海上（sh）还是深圳(sz)
     */
    private String type;

}
