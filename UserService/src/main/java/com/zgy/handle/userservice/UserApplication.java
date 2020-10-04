package com.zgy.handle.userservice;

import com.zgy.handle.common.zuul.context.UserContextInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
//@EnableResourceServer
public class UserApplication {

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

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    public static void main(String[] args){
        SpringApplication.run(UserApplication.class);
    }
}
