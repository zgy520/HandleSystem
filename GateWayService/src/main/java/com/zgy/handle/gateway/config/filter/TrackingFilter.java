package com.zgy.handle.gateway.config.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.zgy.handle.gateway.model.UserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class TrackingFilter extends ZuulFilter {
    private static final int FILTER_ORDER = 1;
    private static final boolean SHOULD_FILTER = true;

    @Autowired
    private FilterUtils filterUtils;

    @Override
    public String filterType() {
        return filterUtils.PRE_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    /**
     * 是否又关联id
     * @return
     */
    private boolean isCorrelationIdPresent(){
        if (filterUtils.getCorrelationId() != null){
            return true;
        }
        return false;
    }

    /**
     * 使用已经存储了用户信息
     * @return
     */
    private boolean isUserInfoPresent(){
        if (filterUtils.getUserInfo() != null){
            return true;
        }
        return false;
    }

    /**
     * 是否存在token
     * @return
     */
    private boolean isAuthorizationPresend(){
        if (filterUtils.getAuthToken() != null){
            return true;
        }
        return false;
    }

    /**
     * 产生关联id
     * @return
     */
    private String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public Object run() throws ZuulException {
        if(isCorrelationIdPresent()) {
            //log.info("tmx-correlation-id found in tracking filter: " + filterUtils.getCorrelationId());
        }else {
            filterUtils.setCorrelationId(generateCorrelationId());
            //log.info("tmx-correlation-id generated in tracking filter: " + filterUtils.getCorrelationId());
        }
        if (!isUserInfoPresent()){
            UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            filterUtils.setUserInfo(userDetails);
            //log.info("setting user info: " + userDetails.getUsername());
        }else {
            //log.info("get user info:" + filterUtils.getUserInfo() + ":" + filterUtils.getUserInfo() + ":" + filterUtils.getOrgId());
        }
        return null;
    }
}
