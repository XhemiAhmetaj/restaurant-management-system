package com.ikubinfo.project.restaurantapp.service;

import com.ikubinfo.project.restaurantapp.domain.entity.Payment;

public interface PaymentService {

    Payment addPayment(String method, Double amount);
}
