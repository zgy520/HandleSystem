package com.zgy.handle.userservice.core.error;

import lombok.Getter;

/**
 * 错误吗
 *
 * @author: a4423
 * @date: 2020/9/19 17:52
 */
@Getter
public enum ErrorNum {
    // 异常信息列表，需要根据不同的需要进行分类
    ERROR_SYSTEM(1, "系统异常"),
    ERROR_NOT_FOUND_DATA(100, "未找到指定的数据");
    private final Integer code;
    private final String message;

    ErrorNum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
