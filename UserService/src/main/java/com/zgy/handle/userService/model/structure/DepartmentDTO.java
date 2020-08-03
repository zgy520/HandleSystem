package com.zgy.handle.userService.model.structure;

import com.zgy.handle.userService.model.dto.BaseDTO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
public class DepartmentDTO extends BaseDTO {
    private String name;
    private String code;
    private String type;
    private String note;
    private String parentId;
    private String enterpriseId;
    private String enterpriseName;
    private List<DepartmentDTO> children = new ArrayList<>();
}
