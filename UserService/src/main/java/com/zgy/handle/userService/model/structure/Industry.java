package com.zgy.handle.userService.model.structure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zgy.handle.userService.model.BaseModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "system_industry")
@Data
@Slf4j
public class Industry extends BaseModel {
    private String code;
    private String name;
    private String shortName;
    @ManyToOne
    @JoinColumn(name = "parentId",referencedColumnName = "id")
    @JsonIgnore
    private Industry parent;
}
