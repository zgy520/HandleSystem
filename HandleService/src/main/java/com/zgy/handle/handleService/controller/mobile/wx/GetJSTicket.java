package com.zgy.handle.handleService.controller.mobile.wx;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class GetJSTicket {

    public JsApiTicket getJsApiTicket(String accessToken){
        RestTemplate restTemplate = new RestTemplate();
        JsApiTicket jsApiTicket = null;

        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessToken+"&type=jsapi";


        String result = restTemplate.getForObject(url, String.class);

        JSONObject jsonObject = JSONObject.parseObject(result);

        if (jsonObject != null){
            try {
                jsApiTicket = new JsApiTicket();

                jsApiTicket.setErrorCode(jsonObject.getString("errcode"));
                jsApiTicket.setErrMsg(jsonObject.getString("errmsg"));
                jsApiTicket.setTicket(jsonObject.getString("ticket"));
                jsApiTicket.setExpiresIn(jsonObject.getLong("expires_in"));
            }catch (Exception ex){
                jsApiTicket = null;
                ex.printStackTrace();
                log.info("系统出错了!");
            }
        }else {
            jsApiTicket = null;
            log.info("jsonObject为空，获取token失败");
        }

        return jsApiTicket;
    }
}
