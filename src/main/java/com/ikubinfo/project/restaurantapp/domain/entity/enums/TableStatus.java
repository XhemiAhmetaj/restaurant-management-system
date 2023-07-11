package com.ikubinfo.project.restaurantapp.domain.entity.enums;

import com.ikubinfo.project.restaurantapp.domain.exception.ResourceNotFoundException;

import java.util.Arrays;

public enum TableStatus {

    AVAILABLE("AVAILABLE"),
    RESERVED("RESERVED");

    private String value;

    TableStatus(String value){
        this.value = value;
    }

    public static TableStatus fromValue(String status){
        return Arrays.asList(TableStatus.values())
                .stream().filter(r -> r.value.equals(status))
                .findFirst()
                .orElseThrow(()-> new ResourceNotFoundException(String
                        .format("Role %s not found",status)));
    }

    public String getValue() {
        return value;
    }
}
