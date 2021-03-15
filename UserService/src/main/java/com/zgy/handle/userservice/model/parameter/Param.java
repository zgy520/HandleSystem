package com.zgy.handle.userservice.model.parameter;

import com.zgy.handle.common.model.BaseModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "system_param")
@Slf4j
@Data
public class Param extends BaseModel implements Serializable {
    private String code;
    private String value;
    private boolean visible; // 可见性
    private ParamType paramType; // 参数类型
}
