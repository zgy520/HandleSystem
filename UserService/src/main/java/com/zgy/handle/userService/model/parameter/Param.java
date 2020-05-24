package com.zgy.handle.userService.model.parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zgy.handle.userService.model.BaseModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Table(name = "system_param")
@Slf4j
@Data
public class Param extends BaseModel {
    private String code;
    private String value;
    @ManyToOne
    @JoinColumn(name = "parentId",referencedColumnName = "id")
    @JsonIgnore
    private Param parent; // 上级参数
    @Enumerated(EnumType.STRING)
    ParamType paramType; // 参数类型
}
