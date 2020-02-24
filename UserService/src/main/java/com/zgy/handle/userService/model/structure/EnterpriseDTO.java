package com.zgy.handle.userService.model.structure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class EnterpriseDTO {
    private String id;
    private String code;
    private String name;
    private String shortName;
    private Long parentId;
    private List<EnterpriseDTO> children = new ArrayList<>();
}
