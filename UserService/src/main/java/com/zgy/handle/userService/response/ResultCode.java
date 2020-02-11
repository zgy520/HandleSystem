package com.zgy.handle.userService.response;

public enum ResultCode {
    SUCCESS(10000,"success"),
    RESOURCE_NOT_EXIT(10001,"资源不存在"),
    PARAM_IS_INVALID(10002,"参数不合法"),
    SERVICE_ERROR(50000,"服务器异常");

    private int code;
    private String msg;

    ResultCode(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
