package com.zgy.handle.userservice.model.authority.depart;

import com.zgy.handle.userservice.model.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartUpdateDTO extends BaseDTO {
    private String name;
    private String code;
    private String type;
    private String note;
    private String parentId;
    private String enterpriseId;
}