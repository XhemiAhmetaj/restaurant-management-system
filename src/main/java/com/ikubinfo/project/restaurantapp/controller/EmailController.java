package com.ikubinfo.project.restaurantapp.controller;

import com.ikubinfo.project.restaurantapp.domain.entity.EmailMessage;
import com.ikubinfo.project.restaurantapp.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class  EmailController {

    private final EmailSenderService emailSenderService;

    @PostMapping("/send-email")
    private ResponseEntity sendEmail(@RequestBody EmailMessage emailMessage){
        emailSenderService.sendEmail(emailMessage.getTo(), emailMessage.getSubject(), emailMessage.getMessage());
        return ResponseEntity.ok("Successfully sent!");
    }
}
