package com.zgy.handle.gateway.controller;

import com.zgy.handle.gateway.model.ResponseCode;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(UsernameNotFoundException ex){
        ResponseCode<Object> responseCode = ResponseCode.error(ex.getMessage(),HttpStatus.NOT_FOUND.value());
        return buildResponse(responseCode);
    }


    private ResponseEntity<Object> buildResponse(ResponseCode responseCode){
        return ResponseEntity.badRequest().body(responseCode);
    }
}
