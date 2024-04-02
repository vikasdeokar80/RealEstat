package com.realestate.dto;

import lombok.Data;

@Data
public class ResponseInquiryDto {
    private String message;
    private Object object;
    private String exception;

    public ResponseInquiryDto(String message)
    {
        this.message = message;
    }
}