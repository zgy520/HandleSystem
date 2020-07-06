package com.zgy.handle.userService.service.auto;

import com.zgy.handle.common.zuul.context.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {
    @Autowired
    private HttpServletRequest request;

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(request.getHeader(UserContext.USER_NAME));
    }


}
