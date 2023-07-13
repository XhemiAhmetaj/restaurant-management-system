package com.ikubinfo.project.restaurantapp;

import com.ikubinfo.project.restaurantapp.service.EmailServiceSenderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class RestaurantManagementAppApplication {
//	@Autowired
//	private EmailServiceSenderImpl emailServiceSender;

	public static void main(String[] args) {
		SpringApplication.run(RestaurantManagementAppApplication.class, args);
	}

//	@EventListener(ApplicationReadyEvent.class)
//	public void sendEmail(){
//		emailServiceSender.sendEmail("xh.ahmetaj22@gmail.com", "Test", "Hello!");
//	}
}
