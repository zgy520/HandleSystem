package com.zgy.handle.userService.model.structure;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
public class DepartmentDTO {
    private String id;
    private String name;
    private String code;
    private String type;
    private String note;
    private String parentId;
    private String enterpriseId;
    private List<DepartmentDTO> children = new ArrayList<>();
}
