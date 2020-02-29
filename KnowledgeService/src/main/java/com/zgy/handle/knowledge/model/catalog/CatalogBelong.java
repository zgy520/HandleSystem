package com.zgy.handle.knowledge.model.catalog;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Slf4j
//@Entity(name = "kr_catalog_belong")
public class CatalogBelong {
    @EmbeddedId
    private CatalogBelongPK catalogBelongPK;
    @ManyToOne
    @MapsId(value = "catalogId")
    @JoinColumn(name = "catalogId")
    @JsonIgnore
    private Catalog catalog;

    //@MapsId(value = "businessId")
    //private Long businessId;

    private Integer serial; // 序号
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    private Date createdTime; // 创建时间
}
