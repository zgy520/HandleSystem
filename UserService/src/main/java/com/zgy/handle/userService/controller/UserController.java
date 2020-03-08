package com.zgy.handle.userService.controller;

import com.zgy.handle.userService.model.user.Account;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


@RestController
@RequestMapping(value = "user")
@Slf4j
@ApiIgnore
public class UserController {
    @ApiOperation("新增用户接口")
    @PostMapping("add")
    public boolean addUser(Account user){
        return false;
    }
    @GetMapping(value = "/find/{id}")
    public Account findById(@PathVariable("id") String id){
        return new Account();
    }
    @PutMapping(value = "update")
    public boolean update(Account user){
        return true;
    }
    @DeleteMapping("delete/{id}")
    public boolean delete(@PathVariable("id") String id){
        return true;
    }
}
