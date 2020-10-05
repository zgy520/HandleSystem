package com.zgy.handle.userservice.controller;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userservice.core.error.ErrorNum;
import com.zgy.handle.userservice.core.exception.BusinessException;
import com.zgy.handle.userservice.core.exception.NotFoundException;
import com.zgy.handle.userservice.exception.ParamException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import javax.persistence.RollbackException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author a4423
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex){
        ResponseCode<Object> responseCode = ResponseCode.error(ex.getMessage(),HttpStatus.NOT_FOUND.value());
        return buildResponse(responseCode);
    }

    /**
     * 根据ID无法找到数据的异常
     * @param ex
     * @return
     */
    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> notFoundException(NotFoundException ex) {
        ResponseCode<Object> responseCode = ResponseCode.error(ex.getMessage(), ErrorNum.ERROR_NOT_FOUND_DATA.getCode());
        return buildResponse(responseCode);
    }

    /**
     * 业务异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<Object> businessException(BusinessException ex) {
        ResponseCode<Object> responseCode = ResponseCode.error(ex.getMessage(), ex.getErrorNum().getCode());
        return buildResponse(responseCode);
    }

    @ExceptionHandler(ParamException.class)
    protected ResponseEntity<Object> handleParamException(ParamException ex){
        ResponseCode<Object> responseCode = ResponseCode.error(ex.getMessage(),HttpStatus.BAD_REQUEST.value());
        return buildResponse(responseCode);
    }

    /**
     * hibernate 参数绑定异常
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleBindException(MethodArgumentNotValidException ex) {
        Set<String> msgSet = ex.getBindingResult().getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toSet());


        ResponseCode<Object> responseCode = ResponseCode.error(msgSet.stream().collect(Collectors.joining(";")),HttpStatus.BAD_REQUEST.value());
        return buildResponse(responseCode);
    }

    @ExceptionHandler(RollbackException.class)
    public ResponseEntity<Object> handleNotValidException(RollbackException ex){

        String errMessage = ex.getCause().getMessage();

        List<String> listErrMessage = getListErrMessage(errMessage);
        StringBuilder messageBuilder = new StringBuilder();
        for (String errMsg : listErrMessage){
            //String message = listErrMessage.stream().collect(Collectors.joining(","));
            String message = errMsg.split(",")[0].split(":")[1];
            messageBuilder.append(message);
            messageBuilder.append(",");
        }
       log.info("获取到的错误数据为:" + messageBuilder.substring(0,messageBuilder.length() - 1).toString());


        //ResponseCode<Object> responseCode = ResponseCode.error(listErrMessage.stream().collect(Collectors.joining(",")),HttpStatus.BAD_REQUEST.value());
        ResponseCode<Object> responseCode = ResponseCode.error(messageBuilder.toString(),HttpStatus.BAD_REQUEST.value());
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
