package com.zgy.handle.userService.controller.validate;

import com.zgy.handle.common.response.ResponseCode;
import com.zgy.handle.userService.service.email.IMailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "rootValidate")
@Api(tags = "根节点验证")
@Slf4j
public class RootValidateController {
    /*@Autowired
    private IMailService mailService;*/

    @GetMapping(value = "test")
    @ApiOperation(value = "验证服务，成功后，会返回对饮过的msg为：validate Success!")
    private ResponseCode<String> validate(){
        ResponseCode<String> responseCode = ResponseCode.sucess();
        responseCode.setMsg("validate Success!");
        return responseCode;
    }

    /*@GetMapping(value = "sendMail")
    private void sendmAIL(){
        mailService.sendSimpleMail("a442391947@outlook.com","handles授权结果","你的授权未通过，请确认!");
    }*/
}
