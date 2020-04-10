package com.zgy.handle.knowledge.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class ResourceCommentDTO {
    private String id;
    private Long personalId;
    private String note;
    private String content;
    private String creator;
    private Date createTime;
    private ResourceType resourceType; // 资源类型
    private Long resourceId; // 资源Id，如文件id，链接id等
}
