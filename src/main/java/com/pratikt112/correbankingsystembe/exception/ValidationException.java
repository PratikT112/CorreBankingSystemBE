package com.pratikt112.correbankingsystembe.exception;

public class ValidationException extends BankingSystemException{

    public ValidationException(String errorCode, String message, String userMessage) {
        super(errorCode, message, userMessage);
    }

    public ValidationException(String errorCode, String message, String userMessage, Throwable cause) {
        super(errorCode, message, userMessage, cause);
    }
}
