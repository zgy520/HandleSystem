package com.zgy.handle.userservice.controller;

import com.zgy.handle.userservice.model.user.Account;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "user")
@Slf4j
@Api(tags = "用户相关文档")
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
