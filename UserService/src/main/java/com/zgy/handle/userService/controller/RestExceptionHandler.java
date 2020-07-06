package com.zgy.handle.userService.controller;

import com.alibaba.fastjson.JSONObject;
import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userService.exception.ParamException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.persistence.RollbackException;
import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
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

    @ExceptionHandler(ParamException.class)
    protected ResponseEntity<Object> handleParamException(ParamException ex){
        /*ApiResponse apiResponse = new ApiResponse(HttpStatus.NOT_FOUND);
        apiResponse.setMessage(ex.getMessage());
        apiResponse.setDebugMessage(ex.getLocalizedMessage());
        return buildResponseEntity(apiResponse);*/
        ResponseCode<Object> responseCode = ResponseCode.error(ex.getMessage(),HttpStatus.BAD_REQUEST.value());
        return buildResponse(responseCode);
    }

    @ExceptionHandler(RollbackException.class)
    public ResponseEntity<Object> handleNotValidException(RollbackException ex){

        String errMessage = ex.getCause().getMessage();

        List<String> listErrMessage = getListErrMessage(errMessage);
        /*StringBuilder messageBuilder = new StringBuilder();
        for (String errMsg : listErrMessage){
            //String message = listErrMessage.stream().collect(Collectors.joining(","));
            JSONObject jsonObject = JSONObject.parseObject(errMsg);
            log.info(jsonObject.toJSONString());
            messageBuilder.append(jsonObject.getString("message"));
        }
       log.info("获取到的错误数据为:" + messageBuilder.toString());*/


        ResponseCode<Object> responseCode = ResponseCode.error(listErrMessage.stream().collect(Collectors.joining(",")),HttpStatus.BAD_REQUEST.value());
        return buildResponse(responseCode);

    }

    /**
     * 从实体验证中获取消息
     * @param msg
     * @return
     */
    private List<String> getListErrMessage(String msg){

        Stream<String> stream = Arrays.stream(msg.split("\n"))
                .filter(s -> s.contains("\t"))
                .map(s -> s.replaceAll("^([^\\{]+)\\{", ""))
                .map(s -> s.replaceAll("[\"]", ""))
                .map(s -> s.replaceAll("=", ":"))
                .map(s -> s.replaceAll("interpolatedMessage", "message"))
                .map(s -> s.replaceAll("\\{|\\}(, *)?", ""));

        return stream.collect(Collectors.toList());
    }

    private ResponseEntity<Object> buildResponse(ResponseCode responseCode){
        return ResponseEntity.badRequest().body(responseCode);
    }
}
