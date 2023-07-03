package com.ikubinfo.project.restaurantapp.domain.entity.enums;

import com.ikubinfo.project.restaurantapp.domain.exception.ResourceNotFoundException;

import java.util.Arrays;

public enum Measurement {

    MILILITER("ml"),
    GRAM("gr");

    private String value;

    Measurement(String value){
        this.value = value;
    }

    public static Measurement fromValue(String measurement){
        return Arrays.asList(Measurement.values())
                .stream().filter(r -> r.value.equals(measurement))
                .findFirst()
                .orElseThrow(()-> new ResourceNotFoundException(String
                        .format("Role %s not found",measurement)));
    }

    public String getValue() {
        return value;
    }
}
