package com.zgy.handle.gateway.config.security;

import java.io.Serializable;

public class JwtResponse implements Serializable {
    private final String data;
    private final int code;

    public JwtResponse(String jwttoken){
        this.data = jwttoken;
        this.code = 2000;
    }

    public String getData(){
        return this.data;
    }

    public int getCode() {
        return code;
    }
}
