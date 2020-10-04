package com.zgy.handle.common.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class SelectDTO {
    private String value;
    private String label;
    private String key;
}
