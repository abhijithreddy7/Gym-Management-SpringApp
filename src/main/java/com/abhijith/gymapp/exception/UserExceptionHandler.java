package com.abhijith.gymapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException(Exception exc){

        UserErrorResponse error = new UserErrorResponse(HttpStatus.BAD_REQUEST.value(),
                exc.getMessage(),System.currentTimeMillis());

        return new ResponseEntity<UserErrorResponse>(error,HttpStatus.BAD_REQUEST);
    }
}
