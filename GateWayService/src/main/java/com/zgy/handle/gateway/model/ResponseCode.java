package com.zgy.handle.gateway.model;

import lombok.Data;

/**
 * 消息的响应体
 * @param <T>
 */
@Data
public class ResponseCode<T> {
    private boolean success; // 是否成功
    private Integer code; // 代码
    private String msg;
    private T data; // 响应数据

    public static ResponseCode sucess(){
        ResponseCode responseCode = new ResponseCode<>();
        responseCode.setCode(20000);
        responseCode.setSuccess(true);
        return responseCode;
    }

    public static ResponseCode error(String msg){
        ResponseCode responseCode = new ResponseCode();
        responseCode.setSuccess(false);
        responseCode.setMsg(msg);
        return responseCode;
    }
    public static ResponseCode error(String msg,Integer code){
        ResponseCode responseCode = new ResponseCode();
        responseCode.setSuccess(false);
        responseCode.setCode(code);
        responseCode.setMsg(msg);
        return responseCode;
    }

    public ResponseCode(){

    }
    public ResponseCode(T data){
        this.data = data;
    }
}
