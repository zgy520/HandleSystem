package com.zgy.handle.common.service;

import com.zgy.handle.common.zuul.context.UserContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取用户信息的服务
 */
public class RequestUserService {
    @Autowired
    private HttpServletRequest request;

    public String getPostName(){
        return request.getHeader(UserContext.POST_NAME);
    }

    /**
     * 获取当前用户的企业id
     * @return
     */
    public String getEnterpriseId(){
        return request.getHeader(UserContext.ENTERPRISE_ID);
    }

    /**
     * 获取当前用户的部门id
     * @return
     */
    public String getDepartId(){
        return request.getHeader(UserContext.ORG_ID);
    }

    /**
     * 获取用户的个人id
     * @return
     */
    public String getPersonalId(){
        return request.getHeader(UserContext.USER_ID);
    }

    public String getCurUserName(){
        return request.getHeader(UserContext.USER_NAME);
    }
}
