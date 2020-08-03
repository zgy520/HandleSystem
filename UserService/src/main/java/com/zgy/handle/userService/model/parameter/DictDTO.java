package com.zgy.handle.userService.model.parameter;

import com.zgy.handle.userService.model.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DictDTO extends BaseDTO {
    private String note;
    private String code;
    private String name;
    private DictType dictType;
    private String parentId;
    private List<DictDTO> children = new ArrayList<>();
}
