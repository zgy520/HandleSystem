package com.zgy.handle.handleService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HandleApplication {
    public static void main(String[] args){
        SpringApplication.run(HandleApplication.class,args);
    }
}
