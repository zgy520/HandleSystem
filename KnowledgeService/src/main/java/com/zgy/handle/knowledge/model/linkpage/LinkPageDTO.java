package com.zgy.handle.knowledge.model.linkpage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkPageDTO {
    private String id;
    private String title; // 标题
    private String keywords;  // 关键字
    private String linkUrl; // 链接地址
    private String note; // 备注
    private String catalogName;
    private String catalogId;
}
