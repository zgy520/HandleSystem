package com.zgy.handle.common.zuul.context;

import org.springframework.stereotype.Component;

@Component
public class UserContext {
    public static final String CORRELATION_ID = "correlation-id";
    public static final String AUTH_TOKEN     = "Authorization";
    public static final String USER_NAME      =  "username";
    public static final String USER_ID        = "user-id";
    public static final String ORG_ID         = "org-id";
    public static final String POST_ID        = "post-id";

    private static final ThreadLocal<String> correlationId= new ThreadLocal<String>();
    private static final ThreadLocal<String> authToken= new ThreadLocal<String>();
    private static final ThreadLocal<String> userName= new ThreadLocal<String>();
    private static final ThreadLocal<String> userId = new ThreadLocal<String>();
    private static final ThreadLocal<String> orgId = new ThreadLocal<String>();
    private static final ThreadLocal<String> postId = new ThreadLocal<String>();



    public static String getCorrelationId() { return correlationId.get(); }
    public static void setCorrelationId(String cid) {correlationId.set(cid);}

    public static String getUserName() {
        return userName.get();
    }
    public static void setUserName(String uName){
        userName.set(uName);
    }

    public static String getAuthToken() { return authToken.get(); }
    public static void setAuthToken(String aToken) {authToken.set(aToken);}

    public static String getUserId() { return userId.get(); }
    public static void setUserId(String aUser) {userId.set(aUser);}

    public static String getOrgId() { return orgId.get(); }
    public static void setOrgId(String aOrg) {orgId.set(aOrg);}

    public static String getPostId() {return postId.get();}
    public static void setPostId(String oPostId){
        postId.set(oPostId);
    }
}
