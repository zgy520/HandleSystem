package com.zgy.handle.userService.model.parameter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DictDTO {
    private String id;
    private String note;
    private String code;
    private String name;
    private DictType dictType;
    private String parentId;
    private List<DictDTO> children = new ArrayList<>();
}
