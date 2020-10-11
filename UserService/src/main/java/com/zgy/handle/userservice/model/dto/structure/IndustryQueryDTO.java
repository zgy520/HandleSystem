package com.zgy.handle.userservice.model.dto.structure;

import com.zgy.handle.userservice.model.dto.BaseDTO;
import com.zgy.handle.userservice.model.structure.IndustryDTO;
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
public class IndustryQueryDTO  extends BaseDTO {
    private String name;
    private String code;
    private String shortName;
    private String parentId;
    private String note;
    private List<IndustryDTO> children = new ArrayList<>();
}