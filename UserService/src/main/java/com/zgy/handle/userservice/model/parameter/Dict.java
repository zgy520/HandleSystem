package com.zgy.handle.userservice.model.parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zgy.handle.common.model.BaseModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 字典
 * 只含有一级
 */
@Entity
@Table(name = "system_dict")
@Slf4j
@Data
public class Dict extends BaseModel implements Serializable {
    private String code;
    private String name;
    private DictType dictType;
    @ManyToOne
    @JoinColumn(name = "parentId")
    @JsonIgnore
    private Dict parent;
}
