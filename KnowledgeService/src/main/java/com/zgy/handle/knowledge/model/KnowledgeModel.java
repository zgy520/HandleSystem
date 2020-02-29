package com.zgy.handle.knowledge.model;

import com.zgy.handle.knowledge.model.common.ResourceLevel;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class KnowledgeModel extends BaseModel {
    @Enumerated(EnumType.STRING)
    private ResourceLevel resourceLevel; // 资源级别
    private Long businessId; // 级别对应的id，如公司id或者部门id或者个人id
}
