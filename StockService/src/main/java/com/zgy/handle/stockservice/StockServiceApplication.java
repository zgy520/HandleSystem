package com.zgy.handle.stockservice;

import com.zgy.handle.common.zuul.context.UserContextInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author: a4423
 * @date: 2020/10/18 22:56
 */
@SpringBootApplication
public class StockServiceApplication {
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        RestTemplate template = new RestTemplate();

        List interceptors = template.getInterceptors();
        interceptors.add(new UserContextInterceptor());
        template.setInterceptors(interceptors);
        return template;
    }


    public static void main(String[] args) {
        SpringApplication.run(StockServiceApplication.class);
    }
}
