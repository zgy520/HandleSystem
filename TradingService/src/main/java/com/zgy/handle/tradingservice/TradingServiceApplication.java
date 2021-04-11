package com.zgy.handle.tradingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: a4423
 * @date: 2021/4/6 22:07
 */
@EnableDiscoveryClient
@SpringBootApplication
public class TradingServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TradingServiceApplication.class);
    }
}
