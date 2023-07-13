package com.ikubinfo.project.restaurantapp.service;

public interface EmailSenderService {
     void sendEmail(String toEmail, String subject, String body);
}
