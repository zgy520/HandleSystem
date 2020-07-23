package com.zgy.handle.userService.model.authority.depart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartUpdateDTO {
    private String id;
    private String name;
    private String code;
    private String type;
    private String note;
    private String parentId;
    private String enterpriseId;
}
