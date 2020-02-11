package com.zgy.handle.userServer.config.security;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AjaxAuthSucessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Map<String, Object> map = new HashMap<>(4);
        Map<String, Object> data = new HashMap<>(4);
        data.put("token","admin-token");
        map.put("data", data);

        map.put("code", 20000);
        map.put("msg", "登录成功");

        String accessControlAllowOrigin = request.getHeader("Access-Control-Allow-Origin");

        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Credentials", "true");

        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().println(JSON.toJSON(map));
        response.setStatus(HttpServletResponse.SC_OK);
        response.flushBuffer();

    }
}
