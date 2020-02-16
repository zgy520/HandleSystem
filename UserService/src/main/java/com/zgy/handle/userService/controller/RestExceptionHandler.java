package com.zgy.handle.userService.controller;

import com.zgy.handle.common.response.ResponseCode;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex){
        /*ApiResponse apiResponse = new ApiResponse(HttpStatus.NOT_FOUND);
        apiResponse.setMessage(ex.getMessage());
        apiResponse.setDebugMessage(ex.getLocalizedMessage());
        return buildResponseEntity(apiResponse);*/
        ResponseCode<Object> responseCode = ResponseCode.error(ex.getMessage(),HttpStatus.NOT_FOUND.value());
        return buildResponse(responseCode);
    }


    private ResponseEntity<Object> buildResponse(ResponseCode responseCode){
        return ResponseEntity.badRequest().body(responseCode);
    }
}
