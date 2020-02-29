package com.zgy.handle.knowledge.model.linkpage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zgy.handle.knowledge.model.KnowledgeModel;
import com.zgy.handle.knowledge.model.catalog.Catalog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 连接管理
 */
@Entity(name = "kr_link_page")
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LinkPage extends KnowledgeModel {
    private String title; // 标题
    private String keywords;  // 关键字
    private String linkUrl; // 链接地址
    @ManyToOne
    @JoinColumn(name = "catalogId")
    @JsonIgnore
    private Catalog catalog;
}
