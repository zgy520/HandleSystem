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
    ERROR_NOT_FOUND_DATA(100, "未找到指定的数据"),

    // 登录异常
    ERROR_LOGIN_LOGINNAME_NOT_FOUNT(101,"登录名不存在"),

    // 用户部分
    USER_ADD_UNQIURE_ERROR(1000,"登录名重复"),

    // 文件上传异常
    FILE_UPLOAD_NOT_FILE(4000,"请选择需要上传的文件"),
    FILE_UPLOAD_TEMPLATE_ERROR(4001,"导入的excel模板错误");
    private final Integer code;
    private final String message;

    ErrorNum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
