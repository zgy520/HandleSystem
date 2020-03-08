package com.zgy.handle.knowledge.model.common;

import com.zgy.handle.knowledge.model.BaseModel;
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
 * 资源分配
 */
@Data
@Entity(name = "kr_resource_dispatch")
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceDispatch extends KnowledgeModel {
    @Enumerated(EnumType.STRING)
    private ResourceLevel resourceLevel; // 资源级别
    private Long personalLevelId; //人员级别对应的id 级别对应的id，如公司id或者部门id或者个人id
    @Enumerated(EnumType.STRING)
    private ResourceType resourceType; // 资源类型
    private Long resourceId; // 资源Id，如文件id，链接id等

}
