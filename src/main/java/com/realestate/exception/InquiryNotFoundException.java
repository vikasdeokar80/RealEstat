package com.realestate.exception;

import org.springframework.http.HttpStatus;

public class InquiryNotFoundException extends RuntimeException {
    private HttpStatus httpStatus;

    public InquiryNotFoundException(String message) {
        super(message);
    }

    public InquiryNotFoundException(String message, HttpStatus httpStatus) {

        super(message);
        this.httpStatus = httpStatus;
    }
}
