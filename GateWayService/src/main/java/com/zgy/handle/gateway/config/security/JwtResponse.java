package com.zgy.handle.gateway.config.security;

import java.io.Serializable;

public class JwtResponse implements Serializable {
    private final String jwttoken;

    public JwtResponse(String jwttoken){
        this.jwttoken = jwttoken;
    }

    public String getToken(){
        return this.jwttoken;
    }
}
