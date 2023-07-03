package com.ikubinfo.project.restaurantapp.configuration;

import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustumEventPublisher implements AuthenticationEventPublisher {
    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {
        System.err.println("Authentication success "+authentication.getPrincipal());
    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {

    }
}
