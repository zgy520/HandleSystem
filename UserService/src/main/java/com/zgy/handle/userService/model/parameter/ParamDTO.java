package com.zgy.handle.userService.model.parameter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParamDTO {
    private String id;
    private String note;
    private String code;
    private String value;
    private boolean visible; // 可见性
    private ParamType paramType; // 参数类型
}
