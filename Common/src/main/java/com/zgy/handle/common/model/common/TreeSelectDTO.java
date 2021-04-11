package com.zgy.handle.common.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: a4423
 * @date: 2021/3/21 20:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeSelectDTO {
    private String id;
    private String label;
    private String parentId;
    private TreeSelectDTO[] children;
    private Boolean isDisabled;
}
