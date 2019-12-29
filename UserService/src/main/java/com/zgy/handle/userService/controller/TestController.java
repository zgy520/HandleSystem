package com.zgy.handle.userService.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @GetMapping(value = "index")
    public String test(){
        log.info("call test index url");
        log.error("xxx");
        return "welcome to my test user Service!";
    }
}
