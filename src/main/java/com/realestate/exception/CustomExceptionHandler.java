package com.realestate.exception;

import com.realestate.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(UserDisabledException.class)
    public ResponseEntity<ApiResponse> userDisabledExceptionHandler(UserDisabledException ex){

        String message=ex.getMessage();
        ApiResponse apiResponse =new ApiResponse(message,false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){

        String message=ex.getMessage();
        ApiResponse apiResponse =new ApiResponse(message,false);
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ApiResponse> invalidCredentialsExceptionHandler(InvalidCredentialsException ex){

        String message=ex.getMessage();
        ApiResponse apiResponse =new ApiResponse(message,false);
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ApiResponse> userAlreadyExistExceptionHandler(UserAlreadyExistException ex){

        String message=ex.getMessage();
        ApiResponse apiResponse =new ApiResponse(message,false);
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
    }
}
