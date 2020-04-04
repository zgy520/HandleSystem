package com.zgy.handle.knowledge.model.common;

import com.zgy.handle.knowledge.model.KnowledgeModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * 资源评论
 */
@Data
@Entity(name = "kr_resource_comment")
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceComment extends KnowledgeModel {
    @Enumerated(EnumType.STRING)
    private ResourceType resourceType; // 资源类型
    private Long resourceId; // 资源Id，如文件id，链接id等
    private Long personalId; // 评论人员id
    private String content; // 评人内容

}
