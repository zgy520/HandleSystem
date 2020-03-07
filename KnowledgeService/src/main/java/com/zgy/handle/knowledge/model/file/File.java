package com.zgy.handle.knowledge.model.file;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zgy.handle.knowledge.model.KnowledgeModel;
import com.zgy.handle.knowledge.model.catalog.Catalog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

/**
 * 文件管理
 */
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "kr_file")
public class File extends KnowledgeModel {
    private String name;
    private String fileType; // 文件类型
    private String filePath; // 文件路径
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] data;
    @ManyToOne
    @JoinColumn(name = "catalogId")
    @JsonIgnore
    private Catalog catalog;
}
