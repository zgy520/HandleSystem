package com.zgy.handle.common.model.page;

import lombok.Data;

/**
 * 前台传递过来的分页信息
 * @author: a4423
 * @date: 2020/11/22 19:26
 */
@Data
public class PageInfo {
    /**
     * 当前页码
     */
    private Integer current;
    /**
     * 每页请求的条数
     */
    private Integer pageSize;
    /**
     * 排序信息
     */
    private String sorter;
}
