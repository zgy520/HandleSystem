package com.zgy.handle.timetradingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: a4423
 * @date: 2021/4/14 22:34
 */
@EnableDiscoveryClient
@SpringBootApplication
public class TimeTradingServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TimeTradingServiceApplication.class);
    }
}
