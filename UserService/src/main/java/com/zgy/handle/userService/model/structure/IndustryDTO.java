package com.zgy.handle.userService.model.structure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndustryDTO {
    private String id;
    private String name;
    private String code;
    private String shortName;
    private String parentId;
    private String note;
    private List<IndustryDTO> children = new ArrayList<>();
}
