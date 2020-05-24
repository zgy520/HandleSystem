package com.zgy.handle.userService.model.parameter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ParamDTO {
    private String id;
    private String code;
    private String value;
    private String parentId;
    private String note;
    @ApiModelProperty(hidden = true)
    ParamType paramType; // 参数类型
}
