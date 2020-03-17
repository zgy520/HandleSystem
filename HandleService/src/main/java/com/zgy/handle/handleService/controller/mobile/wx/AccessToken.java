package com.zgy.handle.handleService.controller.mobile.wx;

import lombok.Data;

@Data
public class AccessToken {
    private String accessToken;
    private Long expiresIn;
}
