package com.zgy.handle.knowledge.model.solution;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class SolutionDTO {
    private String question; // 问题
    private String measure; // 解决措施
    private String thinking; // 解决思路
    private String url; // 外部链接
    private String catalogName;
    private String catalogId;
}
