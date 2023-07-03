package com.ikubinfo.project.restaurantapp.domain.exception;

public class BadRequestException extends RuntimeException  {

    private String errorMessage;
    public BadRequestException (String errorMessage) {
        this.errorMessage=errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


}
