package com.zgy.handle.userservice.core.exception;

import com.zgy.handle.userservice.core.error.ErrorNum;
import lombok.Getter;

/**
 * @author: a4423
 * @date: 2020/9/19 18:01
 */
@Getter
public class BusinessException extends RuntimeException {
    // 错误码信息
    private ErrorNum errorNum;

    public BusinessException(ErrorNum errorNum) {
        super(errorNum.getMessage());
        this.errorNum = errorNum;
    }
}
