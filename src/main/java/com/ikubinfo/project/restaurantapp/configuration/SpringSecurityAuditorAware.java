package com.ikubinfo.project.restaurantapp.configuration;

import com.ikubinfo.project.restaurantapp.domain.entity.User;
import com.ikubinfo.project.restaurantapp.repository.UserRepository;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import java.util.Optional;


public class SpringSecurityAuditorAware implements AuditorAware<User> {
    private UserRepository userRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public SpringSecurityAuditorAware(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public Optional<User> getCurrentAuditor() {
//        String sub = null;
//        if(SecurityContextHolder.getContext().getAuthentication()!=null){
//            Jwt principal = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            sub = (String) principal.getClaims().get("sub");
//        }
//        return sub==null?Optional.empty():userRepository.findByEmail(sub);
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var sub = (String) jwt.getClaims().get("sub");
        entityManager.setFlushMode(FlushModeType.COMMIT);
        Optional<User> u  = userRepository.findByEmail(sub);
        entityManager.setFlushMode(FlushModeType.AUTO);
        return u;
    }


}
