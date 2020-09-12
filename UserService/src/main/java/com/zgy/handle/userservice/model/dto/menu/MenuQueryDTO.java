package com.zgy.handle.userservice.model.dto.menu;

import com.zgy.handle.userservice.model.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class MenuQueryDTO extends BaseDTO {
    private String code;
    private String name;
    private String url;
    private String note;
    private String icon;
    private int serial;
    private String parentId;
    private List<MenuQueryDTO> children = new ArrayList<>();
}
