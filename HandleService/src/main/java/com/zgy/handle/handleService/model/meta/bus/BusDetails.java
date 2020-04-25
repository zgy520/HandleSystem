package com.zgy.handle.handleService.model.meta.bus;

import com.zgy.handle.handleService.model.common.BaseModel;
import com.zgy.handle.handleService.model.meta.structure.enterprise.MetaBody;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "handle_meta_bus_data")
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Data
public class BusDetails extends BaseModel {
    private String fieldValue; // 字段值
    @ManyToOne
    @JoinColumn(name = "primaryId")
    private BusPrimary busPrimary; // 主键值
    @ManyToOne
    @JoinColumn(name = "bodyId")
    private MetaBody metaBody;
}
