package com.ikubinfo.project.restaurantapp.domain.entity.enums;

import com.ikubinfo.project.restaurantapp.domain.exception.ResourceNotFoundException;

import java.util.Arrays;

public enum OrderStatus {
//    CANCELLED("CANCELLED"),
    CONFIRMED("CONFIRMED"),
    DELIVERED("DELIVERED"),
    IN_PROGRESS("IN_PROGRESS"),
//    DELIVERED("DELIVERED"),
    CREATED("CREATED");

    private String value;

    OrderStatus(String value){
        this.value=value;
    }

    public static OrderStatus fromValue(String orderStatus){
        return Arrays.asList(OrderStatus.values())
                .stream().filter(r -> r.value.equals(orderStatus))
                .findFirst()
                .orElseThrow(()-> new ResourceNotFoundException(String
                        .format("Order Status %s not found",orderStatus)));
    }

    public String getValue() {
        return value;
    }
}
