package com.ikubinfo.project.restaurantapp;

import com.ikubinfo.project.restaurantapp.configuration.SpringSecurityAuditorAware;
import com.ikubinfo.project.restaurantapp.domain.entity.User;
import com.ikubinfo.project.restaurantapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.oauth2.jwt.Jwt;

import javax.swing.text.html.parser.Entity;

@SpringBootApplication
@EnableJpaAuditing
public class RestaurantManagementAppApplication {
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(RestaurantManagementAppApplication.class, args);
	}

	@Bean
	public AuditorAware<User> auditorAware() {
		return new SpringSecurityAuditorAware(userRepository);
	}

}
