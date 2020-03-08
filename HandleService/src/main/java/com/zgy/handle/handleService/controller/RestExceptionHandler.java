package com.zgy.handle.handleService.controller;

import com.zgy.handle.common.response.ResponseCode;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex){
        ResponseCode<Object> responseCode = ResponseCode.error(ex.getMessage(),HttpStatus.NOT_FOUND.value());
        return buildResponse(responseCode);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleEntityConstrainVlilationException(ConstraintViolationException ex){
        ResponseCode<Object> responseCode = ResponseCode.error(ex.getMessage(),HttpStatus.NOT_FOUND.value());
        return buildResponse(responseCode);
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponse(ResponseCode.error(ex.getMessage(),status.value()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponse(ResponseCode.error("验证异常，请确认",status.value()));
    }

    private ResponseEntity<Object> buildResponse(ResponseCode responseCode){
        return ResponseEntity.badRequest().body(responseCode);
    }
}
