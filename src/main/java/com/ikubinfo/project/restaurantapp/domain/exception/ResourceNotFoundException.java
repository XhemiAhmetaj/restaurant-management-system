package com.ikubinfo.project.restaurantapp.domain.exception;

public class ResourceNotFoundException extends RuntimeException{
    private String errorMessage;
    public ResourceNotFoundException (String errorMessage) {
        this.errorMessage=errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
