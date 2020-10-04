package com.zgy.handle.common.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 穿梭框dto
 */
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class TransferDTO {
    private String key;
    private String label;
    private boolean selected = false;
}
