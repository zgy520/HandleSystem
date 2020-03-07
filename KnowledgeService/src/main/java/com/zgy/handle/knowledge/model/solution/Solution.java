package com.zgy.handle.knowledge.model.solution;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zgy.handle.knowledge.model.KnowledgeModel;
import com.zgy.handle.knowledge.model.catalog.Catalog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 解决方案管理
 */
@Entity(name = "kr_solution")
@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class Solution extends KnowledgeModel {
    private String question; // 问题
    private String measure; // 解决措施
    private String thinking; // 解决思路
    private String url; // 外部链接
    @ManyToOne
    @JoinColumn(name = "catalogId")
    @JsonIgnore
    private Catalog catalog;
}
