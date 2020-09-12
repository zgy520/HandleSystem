package com.zgy.handle.userservice.model.authority.depart;

import com.zgy.handle.userservice.model.dto.BaseDTO;
import com.zgy.handle.userservice.model.structure.DepartmentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartQueryDTO extends BaseDTO {
    private String name;
    private String code;
    private String type;
    private String note;
    private String parentId;
    private String enterpriseId;
    private String parentName;
    private String enterpriseName;
    private List<DepartmentDTO> children = new ArrayList<>();
}
