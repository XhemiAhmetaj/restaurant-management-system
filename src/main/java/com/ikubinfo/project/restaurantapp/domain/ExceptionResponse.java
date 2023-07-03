package com.ikubinfo.project.restaurantapp.domain;

import lombok.Data;

import java.util.Date;

@Data
public class ExceptionResponse {
    private Object message;
    private Date timestamp;

    public ExceptionResponse(Object message) {
        this.message = message;
        this.timestamp = new Date();
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
