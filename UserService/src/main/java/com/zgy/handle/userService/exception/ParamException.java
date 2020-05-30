package com.zgy.handle.userService.exception;

import org.springframework.stereotype.Component;

@Component
public class ParamException extends RuntimeException{
    public ParamException(){

    }

    public ParamException(String message){
        super(message);
    }
}
