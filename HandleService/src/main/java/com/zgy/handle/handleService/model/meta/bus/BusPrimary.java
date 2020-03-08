package com.zgy.handle.handleService.model.meta.bus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zgy.handle.handleService.model.BaseModel;
import com.zgy.handle.handleService.model.meta.structure.enterprise.MetaHeader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "handle_meta_bus_primary")
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Data
public class BusPrimary extends BaseModel {
    private String handleCode; // 业务数据的handle码
    private Long primaryValue; // 主键值

    @ManyToOne
    @JoinColumn(name = "metaHeaderId")
    @JsonIgnore
    private MetaHeader metaHeader;
}
