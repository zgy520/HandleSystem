package com.zgy.handle.handleService.controller.mobile.wx;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class GetToken {

    public AccessToken getToken(String appId, String appScrect){
        RestTemplate restTemplate = new RestTemplate();
        AccessToken accessToken = null;

        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId
                + "&secret=" + appScrect;

        //accessToken = restTemplate.getForObject(url,String.class);

        String result = restTemplate.getForObject(url, String.class);

        JSONObject jsonObject = JSONObject.parseObject(result);

        if (jsonObject != null){
            try {
                accessToken = new AccessToken();

                accessToken.setAccessToken(jsonObject.getString("access_token"));
                accessToken.setExpiresIn(jsonObject.getLong("expires_in"));
            }catch (Exception ex){
                accessToken = null;
                ex.printStackTrace();
                log.info("系统出错了!");
            }
        }else {
            accessToken = null;
            log.info("jsonObject为空，获取token失败");
        }

        return accessToken;
    }

}
