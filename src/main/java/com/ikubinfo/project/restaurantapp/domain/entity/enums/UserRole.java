package com.ikubinfo.project.restaurantapp.domain.entity.enums;

import com.ikubinfo.project.restaurantapp.domain.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum UserRole {

    ADMIN("ADMIN"),
    COOKER("COOKER"),
    WAITER("WAITER"),
    RECEPTIONIST("RECEPTIONIST"),
    CUSTOMER("CUSTOMER");

    private String value;

    public static UserRole fromValue(String userRole){
        return Arrays.asList(UserRole.values())
                .stream().filter(r -> r.value.equals(userRole))
                .findFirst()
                .orElseThrow(()-> new ResourceNotFoundException(String
                        .format("Role %s not found",userRole)));
    }

    public String getValue() {
        return value;
    }
}
