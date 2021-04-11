package com.zgy.handle.replayservice.model;

import com.zgy.handle.common.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author: a4423
 * @date: 2021/4/3 12:22
 * 类别
 */
@Entity
@Table(name = "catalog")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Catalog extends BaseModel {
    /**
     * 编码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
}
