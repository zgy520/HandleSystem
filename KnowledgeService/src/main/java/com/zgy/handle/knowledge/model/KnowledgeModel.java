package com.zgy.handle.knowledge.model;

import com.zgy.handle.knowledge.model.common.ResourceLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class KnowledgeModel extends BaseModel {

}
