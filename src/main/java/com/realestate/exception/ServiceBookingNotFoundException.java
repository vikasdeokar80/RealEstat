package com.realestate.exception;


import org.springframework.http.HttpStatus;

public class ServiceBookingNotFoundException extends RuntimeException {
    private HttpStatus httpStatus;

    public ServiceBookingNotFoundException(String message) {
        super(message);
    }

    public ServiceBookingNotFoundException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

