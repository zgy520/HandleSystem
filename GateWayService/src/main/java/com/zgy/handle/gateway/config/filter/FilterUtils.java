package com.zgy.handle.gateway.config.filter;

import com.netflix.zuul.context.RequestContext;
import com.zgy.handle.gateway.model.UserDetails;
import org.springframework.stereotype.Component;


@Component
public class FilterUtils {
    public static final String CORRELATION_ID = "correlation-id";
    public static final String AUTH_TOKEN     = "Authorization";
    public static final String USER_ID        = "user-id";
    public static final String ORG_ID         = "org-id";
    public static final String ENTERPRISE_ID  = "enterprise-id";
    public static final String POST_ID        = "post-id";
    public static final String POST_NAME      =  "post-name";
    public static final String USER_INFO      = "username";
    public static final String PRE_FILTER_TYPE = "pre";
    public static final String POST_FILTER_TYPE = "post";
    public static final String ROUTE_FILTER_TYPE = "route";

    public String getCorrelationId(){
        RequestContext ctx = RequestContext.getCurrentContext();

        if (ctx.getRequest().getHeader(CORRELATION_ID) !=null) {
            return ctx.getRequest().getHeader(CORRELATION_ID);
        }
        else{
            return  ctx.getZuulRequestHeaders().get(CORRELATION_ID);
        }
    }

    public void setCorrelationId(String correlationId){
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader(CORRELATION_ID, correlationId);
    }

    public void setUserInfo(UserDetails userDetails){
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader(USER_INFO, userDetails.getUsername());
        ctx.addZuulRequestHeader(ORG_ID,userDetails.getOrgId());
        ctx.addZuulRequestHeader(POST_ID,userDetails.getPostId());
        ctx.addZuulRequestHeader(USER_ID,userDetails.getUserId());
        ctx.addZuulRequestHeader(POST_NAME,userDetails.getPostName());
        ctx.addZuulRequestHeader(ENTERPRISE_ID,userDetails.getEnterpriseId());
    }

    public final String getUserInfo(){
        RequestContext ctx = RequestContext.getCurrentContext();
        if (ctx.getRequest().getHeader(USER_INFO) !=null) {
            return ctx.getRequest().getHeader(USER_INFO);
        }
        else{
            return  ctx.getZuulRequestHeaders().get(USER_INFO);
        }
    }
    public  final String getOrgId(){
        RequestContext ctx = RequestContext.getCurrentContext();
        if (ctx.getRequest().getHeader(ORG_ID) !=null) {
            return ctx.getRequest().getHeader(ORG_ID);
        }
        else{
            return  ctx.getZuulRequestHeaders().get(ORG_ID);
        }
    }

    public void setOrgId(String orgId){
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader(ORG_ID,  orgId);
    }

    public final String getPostId(){
        RequestContext ctx = RequestContext.getCurrentContext();
        if (ctx.getRequest().getHeader(POST_ID) != null){
            return ctx.getRequest().getHeader(POST_ID);
        }else {
            return ctx.getZuulRequestHeaders().get(POST_ID);
        }
    }
    public void setPostId(String postId){
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader(POST_ID,postId);
    }

    public final String getUserId(){
        RequestContext ctx = RequestContext.getCurrentContext();
        if (ctx.getRequest().getHeader(USER_ID) !=null) {
            return ctx.getRequest().getHeader(USER_ID);
        }
        else{
            return  ctx.getZuulRequestHeaders().get(USER_ID);
        }
    }

    public void setUserId(String userId){
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader(USER_ID,  userId);
    }

    public final String getAuthToken(){
        RequestContext ctx = RequestContext.getCurrentContext();
        return ctx.getRequest().getHeader(AUTH_TOKEN);
    }

    public String getServiceId(){
        RequestContext ctx = RequestContext.getCurrentContext();

        //We might not have a service id if we are using a static, non-eureka route.
        if (ctx.get("serviceId")==null) return "";
        return ctx.get("serviceId").toString();
    }

}
