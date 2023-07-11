package com.ikubinfo.project.restaurantapp.domain.entity.enums;

import com.ikubinfo.project.restaurantapp.domain.exception.ResourceNotFoundException;

import java.util.Arrays;

public enum PaymentMethod {

    MASTERCARD("MASTERCARD"),
    VISA("VISA"),
    PAYPAL("PAYPAL");

    private String value;

    PaymentMethod (String value){
        this.value = value;
    }
    public static PaymentMethod fromValue(String value){
        return Arrays.asList(PaymentMethod.values())
                .stream().filter(r -> r.value.equals(value))
                .findFirst()
                .orElseThrow(()-> new ResourceNotFoundException(String
                        .format("Shipping Status %s not found",value)));
    }

    public String getValue() {
        return value;
    }
}
