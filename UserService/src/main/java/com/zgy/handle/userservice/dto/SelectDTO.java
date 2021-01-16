package com.zgy.handle.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 下来选择框的dto
 * @author: a4423
 * @date: 2021/1/13 6:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectDTO {
    private String key;
    private SelectValueDTO selectValueDTO;
}
