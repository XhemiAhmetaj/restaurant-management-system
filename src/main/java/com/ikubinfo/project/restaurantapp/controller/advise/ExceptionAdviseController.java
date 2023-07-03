package com.ikubinfo.project.restaurantapp.controller.advise;

import com.ikubinfo.project.restaurantapp.domain.ExceptionResponse;
import com.ikubinfo.project.restaurantapp.domain.exception.BadRequestException;
import com.ikubinfo.project.restaurantapp.domain.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdviseController {
    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleBadRequestExceptionException(BadRequestException se) {
        ExceptionResponse res = new ExceptionResponse(se.getErrorMessage());
        return res;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleResourceNotFoundException(ResourceNotFoundException se) {
        ExceptionResponse res = new ExceptionResponse(se.getErrorMessage());
        return res;
    }
}
