package com.zgy.handle.userService.model.dto.menu;

import com.zgy.handle.userService.model.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class BtnDTO extends BaseDTO {
    private String code;
    private String name;
    private String icon;
    private int serial;
    private String note;
}
