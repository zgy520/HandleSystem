package com.zgy.handle.userService.model.structure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class EnterpriseRegShortDTO {
    private String name;
    private String prefix;
    private String checkStatus; // 审核状态
    private String authorStatus; // 授权状态
}
