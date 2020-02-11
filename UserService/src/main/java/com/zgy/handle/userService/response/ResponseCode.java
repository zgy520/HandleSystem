package com.zgy.handle.userService.response;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;

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

/*    private int pageSize; // 页面大小
    private int pageNumer; // 页码*/
    private long totalElements; // 总数量
    private int totalPage; // 总页数



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
    }
}
