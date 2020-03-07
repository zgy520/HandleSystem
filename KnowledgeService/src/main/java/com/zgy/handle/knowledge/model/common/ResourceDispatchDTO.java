package com.zgy.handle.knowledge.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class ResourceDispatchDTO {
    private String id;
    private ResourceLevel resourceLevel; // 资源级别
    private Long personalLevelId;
    private ResourceType resourceType; // 资源类型
    private Long resourceId; // 资源Id，如文件id，链接id等
}
