package com.pratikt112.correbankingsystembe.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String errorCode;
    private String message;
    private String userMessage;
    private LocalDateTime timeStamp;
    private String path;

    public ErrorResponse(String errorCode, String message, String userMessage, String path){
        this.errorCode = errorCode;
        this.message = message;
        this.userMessage = userMessage;
        this.timeStamp = LocalDateTime.now();
        this.path = path;
    }
}
