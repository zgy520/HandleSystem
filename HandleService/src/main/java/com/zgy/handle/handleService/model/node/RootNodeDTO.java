package com.zgy.handle.handleService.model.node;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class RootNodeDTO {
    private String id;
    private String name;
    private String prex;
    private String ip;
    private String location; // 位置
}
