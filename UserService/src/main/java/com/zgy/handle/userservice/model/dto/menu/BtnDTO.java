package com.zgy.handle.userservice.model.dto.menu;

import com.zgy.handle.userservice.model.dto.BaseDTO;
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
//    private Integer serial;
    private String note;
}
