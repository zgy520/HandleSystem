package com.zgy.handle.userService.model.parameter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DictDTO {
    private Long id;
    private String note;
    private String code;
    private String name;
    private DictType dictType;
    private Long parentId;
}
