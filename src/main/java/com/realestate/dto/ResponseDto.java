package com.realestate.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto {

    private String status;
    private String message;

    public ResponseDto(String status, String message) {
        this.status = status;
        this.message = message;
    }
}

