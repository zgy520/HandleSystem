package com.zgy.handle.gateway.config.cros;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                //.allowedOrigins("http://localhost:9528","http://114.115.215.119:8080","http://localhost:8085")
                .allowedOrigins("*")
                .allowedHeaders("*");
    }
}
