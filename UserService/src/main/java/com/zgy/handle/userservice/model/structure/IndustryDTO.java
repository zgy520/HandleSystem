package com.zgy.handle.userservice.model.structure;

import com.zgy.handle.userservice.model.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndustryDTO extends BaseDTO {
    private String name;
    private String code;
    private String shortName;
    private String parentId;
    private String note;
    private List<IndustryDTO> children = new ArrayList<>();
}
