package com.zgy.handle.userservice.core.exception;

import com.zgy.handle.userservice.core.error.ErrorNum;

/**
 * 数据未发现的异常信息
 * @author: a4423
 * @date: 2020/9/19 18:02
 */
public class NotFoundException extends BusinessException{

    public NotFoundException(ErrorNum errorNum) {
        super(errorNum);
    }
}
