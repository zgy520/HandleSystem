package com.zgy.handle.jwtServer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "jwt")
@Slf4j
public class JWTController {
    @RequestMapping(value = "hello")
    public String firstPage(){
        return "Hello World";
    }
}
