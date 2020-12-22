package com.dormakaba.demo.oauth.security.event;

import brave.Tracer;
import com.dormakaba.demo.oauth.service.UserService;
import com.dormakaba.demo.usercommons.model.entity.User;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);

    @Autowired
    private UserService userService;

    @Autowired
    private Tracer tracer;

    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {
        LOG.info(String.format("Success login: %s", authentication.getName()));
    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException e, Authentication authentication) {
        LOG.error(String.format("Failed login: %s", e.getMessage()));
        tracer.currentSpan().tag("errors", String.format("Failed login: %s", e.getMessage()));
        try {
            User user = userService.findByUsername(authentication.getName());
            if (user.getLoginAttempts() == null) {
                user.setLoginAttempts(0);
            }
            user.setLoginAttempts(user.getLoginAttempts() + 1);
            if (user.getLoginAttempts() >= 3) {
                LOG.error(String.format("Disabling user %s after 3 failed login attempts", user.getUsername()));
                user.setEnabled(false);
            }
            userService.update(user, user.getId());
        } catch (FeignException ex) {
            LOG.error(String.format("User does %s not exist", authentication.getName()));
        }

    }

}
