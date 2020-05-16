package com.zgy.handle.handleService.model.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class RootInfoDTO {
    private String id;
    private String nodePrex;
    private String ip;
    private String port;
    private String note;
}
