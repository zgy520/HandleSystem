package com.zgy.handle.OrgServer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "org")
public class OrgController {
    @GetMapping(value = "info")
    public String getOrgInfo(){
        return "get org Info";
    }
}
