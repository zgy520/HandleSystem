package com.zgy.handle.handleService.controller.mobile.wx;

import lombok.Data;

@Data
public class JsApiTicket {
    public String errorCode;
    public String errMsg;
    public String ticket;
    public Long expiresIn;
}
