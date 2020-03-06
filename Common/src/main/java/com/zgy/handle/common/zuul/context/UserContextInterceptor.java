package com.zgy.handle.common.zuul.context;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class UserContextInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        HttpHeaders headers = request.getHeaders();
        headers.add(UserContext.CORRELATION_ID, UserContextHolder.getContext().getCorrelationId());
        headers.add(UserContext.AUTH_TOKEN, UserContextHolder.getContext().getAuthToken());
        headers.add(UserContext.USER_NAME, UserContextHolder.getContext().getUserName());
        headers.add(UserContext.POST_ID,UserContextHolder.getContext().getPostId());
        headers.add(UserContext.ORG_ID,UserContextHolder.getContext().getOrgId());
        headers.add(UserContext.USER_ID,UserContextHolder.getContext().getUserId());
        headers.add(UserContext.POST_NAME,UserContextHolder.getContext().getPostName());


        return execution.execute(request, body);
    }
}