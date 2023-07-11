package com.ikubinfo.project.restaurantapp.service.impl;

import com.ikubinfo.project.restaurantapp.domain.entity.Payment;
import com.ikubinfo.project.restaurantapp.domain.entity.enums.PaymentMethod;
import com.ikubinfo.project.restaurantapp.repository.PaymentRepository;
import com.ikubinfo.project.restaurantapp.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment addPayment(String method, Double amount) {
        Payment payment = new Payment();
        payment.setPaymentMethod(PaymentMethod.fromValue(method));
        payment.setAmount(amount);
        log.info("Payment {}",payment.getAmount());
        return paymentRepository.save(payment);
    }
}
