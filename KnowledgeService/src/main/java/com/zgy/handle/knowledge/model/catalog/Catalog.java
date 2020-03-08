package com.zgy.handle.knowledge.model.catalog;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zgy.handle.knowledge.model.KnowledgeModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 目录管理
 */
@Entity(name = "kr_catalog")
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Catalog extends KnowledgeModel {
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "parentId")
    @JsonIgnore
    private Catalog parent; // 上级目录
}
