package com.zgy.handle.replayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: a4423
 * @date: 2021/4/3 12:10
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ReplayServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReplayServiceApplication.class);
    }
}
