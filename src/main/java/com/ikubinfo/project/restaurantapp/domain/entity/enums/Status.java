package com.ikubinfo.project.restaurantapp.domain.entity.enums;

import com.ikubinfo.project.restaurantapp.domain.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum Status {

    AVAILABLE("AVAILABLE"),
    RESERVED("RESERVED");

    private String value;

    public static Status fromValue(String status){
        return Arrays.asList(Status.values())
                .stream().filter(r -> r.value.equals(status))
                .findFirst()
                .orElseThrow(()-> new ResourceNotFoundException(String
                        .format("Role %s not found",status)));
    }

    public String getValue() {
        return value;
    }
}
