package com.zgy.handle.gateway.config.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
            log.info("tmx-correlation-id found in tracking filter: " + filterUtils.getCorrelationId());
        }else {
            filterUtils.setCorrelationId(generateCorrelationId());
            log.info("tmx-correlation-id generated in tracking filter: " + filterUtils.getCorrelationId());
        }
        RequestContext ctx = RequestContext.getCurrentContext();
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        filterUtils.setUserInfo(userDetails);
        log.info("获取到的用户信息为: " + userDetails.getUsername());
        log.info("Processing incoming request for: ",
                ctx.getRequest().getRequestURI());
        return null;
    }
}
