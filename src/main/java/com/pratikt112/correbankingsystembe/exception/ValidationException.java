package com.pratikt112.correbankingsystembe.exception;

public class ValidationException extends BankingSystemException{

    public ValidationException(String errorCode, String message, String userMessage) {
        super(errorCode, message, userMessage);
    }

    public ValidationException(String errorCode, String message, String userMessage, Throwable cause) {
        super(errorCode, message, userMessage, cause);
    }

    public ValidationException(String field, String message){
        super("VALIDATION_ERROR",
                "Validation failed for field '" + field + "': " + message,
                "Invalid data provided: " + message);
    }

    public ValidationException(String message) {
        super("VALIDATION_ERROR",
                "Validation failed: " + message,
                "Invalid data provided: " + message);
    }

    public ValidationException(String message, Throwable cause) {
        super("VALIDATION_ERROR",
                "Validation failed: " + message,
                "Invalid data provided: " + message,
                cause);
    }
}
