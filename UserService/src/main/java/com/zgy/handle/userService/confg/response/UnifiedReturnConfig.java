package com.zgy.handle.userService.confg.response;

import com.zgy.handle.userService.controller.ControllerConfig;
import com.zgy.handle.userService.response.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.PostConstruct;

//@EnableWebMvc
//@Configuration
@Slf4j
public class UnifiedReturnConfig {

    @RestControllerAdvice("com.zgy.handle.userService.controller")
    static class ResponseCodeAdvice implements ResponseBodyAdvice<Object>{
        @Override
        public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
            return true;
        }

        @Override
        public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
            if (body instanceof ResponseCode){
                return body;
            }
            return new ResponseCode<>(body);
        }
    }
}
