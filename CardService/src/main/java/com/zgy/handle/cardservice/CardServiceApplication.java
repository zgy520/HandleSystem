package com.zgy.handle.cardservice;

import com.zgy.handle.common.zuul.context.UserContextInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

/**
 * @author: a4423
 * @date: 2020/9/26 20:15
 */
@SpringBootApplication
public class CardServiceApplication {
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        RestTemplate template = new RestTemplate();

        List interceptors = template.getInterceptors();
        if (interceptors == null){
            template.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
        }else {
            interceptors.add(new UserContextInterceptor());
            template.setInterceptors(interceptors);
        }

        return template;
    }

    public static void main(String[] args){
        SpringApplication.run(CardServiceApplication.class);
    }
}

