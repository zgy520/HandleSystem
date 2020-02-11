package com.zgy.handle.userService.model.parameter;

import com.zgy.handle.userService.model.BaseModel;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "system_param")
@Slf4j
public class Param extends BaseModel {
    private String code;
    private String value;
}
