package com.zgy.handle.userService.response.Api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ApiValidateResponse extends ApiSubResponse {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    ApiValidateResponse(String object,String message){
        this.object = object;
        this.message = message;
    }
}
