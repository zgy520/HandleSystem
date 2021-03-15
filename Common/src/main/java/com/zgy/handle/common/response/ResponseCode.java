package com.zgy.handle.common.response;

import lombok.Data;
import org.springframework.data.domain.Page;

/**
 * 消息的响应体
 * @author a4423
 * @param <T>
 */
@Data
public class ResponseCode<T> {
    // 是否成功
    private boolean success;
    // 代码
    private Integer code;
    private String msg;
    // 响应数据
    private T data;
    // 总数量
    private long totalElements;
    // 总页数
    private int totalPage;


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

    public ResponseCode setPageInfo(Page pageInfo){
        this.totalElements = pageInfo.getTotalElements();
        this.totalPage= pageInfo.getTotalPages();
        return this;
    }

    public ResponseCode setDataInfo(Page page){
        this.totalPage = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.data = (T) page.getContent();
        return this;
    }

    public ResponseCode(){

    }
    public ResponseCode(T data){
        this.data = data;
        this.success = true;
    }
}
