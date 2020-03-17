package com.zgy.handle.handleService.controller.mobile.wx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AccessTokenTask {
    @Autowired
    private GetToken getToken;
    @Autowired
    private Parameter parameter;
    @Autowired
    private GetJSTicket getJSTicket;

    @Scheduled(initialDelay = 1000,fixedDelay = 7000 * 1000)
    public void getAccessToken(){
        try {
            String token = getToken.getToken(parameter.getAppId(),parameter.getAppSecret()).getAccessToken();
            // 将获取导得token放到内存中
            WeixinToken.token = token;
            log.info("获取到得微信accessToken为:"+token);
            String jsApiTicket = getJSTicket.getJsApiTicket(token).getTicket();
            WeixinToken.jsapi_ticket = jsApiTicket;
            log.info("获取到得微信jsApiTicket为:" + jsApiTicket);
        }catch (Exception ex){
            log.info("获取微信token出错");
            ex.printStackTrace();
        }
    }
}
