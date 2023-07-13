package com.ikubinfo.project.restaurantapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class RestaurantManagementAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantManagementAppApplication.class, args);
	}

}
