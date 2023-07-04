package com.ikubinfo.project.restaurantapp.configuration;

import com.ikubinfo.project.restaurantapp.domain.entity.User;
import com.ikubinfo.project.restaurantapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j

public class SpringSecurityAuditorAware implements AuditorAware<User> {


    private UserRepository userRepository;

    public SpringSecurityAuditorAware(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public Optional<User> getCurrentAuditor() {
//        return Optional.ofNullable(SecurityContextHolder.getContext())
//                .map(SecurityContext::getAuthentication)
//                .filter(Authentication::isAuthenticated)
//                .map(Authentication::getPrincipal)
//                .map(User.class::cast);]

        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var sub = (String) jwt.getClaims().get("sub");
        log.info("Sub = {}",sub);
        return userRepository.findByEmail(sub)

















                ;
    }
}
