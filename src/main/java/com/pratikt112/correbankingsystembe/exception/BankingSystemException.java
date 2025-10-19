package com.pratikt112.correbankingsystembe.exception;


import lombok.Getter;


@Getter
public abstract class BankingSystemException extends RuntimeException{
    private final String errorCode;
    private final String userMessage;

    public BankingSystemException(String errorCode, String message, String userMessage){
        super(message);
        this.errorCode = errorCode;
        this.userMessage = userMessage;
    }

    public BankingSystemException(String errorCode, String message, String userMessage, Throwable cause){
        super(message, cause);
        this.errorCode = errorCode;
        this.userMessage = userMessage;
    }

}
