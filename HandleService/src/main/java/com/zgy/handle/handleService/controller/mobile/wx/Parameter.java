package com.zgy.handle.handleService.controller.mobile.wx;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "parameter")
public class Parameter {
    /**
     * 小程序得id
     */
    private String appId;
    /**
     * 小程序得appScret
     */
    private String appSecret;
}
