package com.pratikt112.correbankingsystembe.exception;


import lombok.Getter;


@Getter
public abstract class BankingSystemException extends RuntimeException{
    private final String errorCode;
    private final String errDesc;
    private final String userMessage;

    public BankingSystemException(String errorCode, String message, String errDesc, String userMessage){
        super(message);
        this.errorCode = errorCode;
        this.errDesc = errDesc;
        this.userMessage = userMessage;
    }

    public BankingSystemException(String errorCode, String message, String userMessage, Throwable cause, String errDesc){
        super(message, cause);
        this.errorCode = errorCode;
        this.userMessage = userMessage;
        this.errDesc = errDesc;
    }

    public BankingSystemException(String errDesc, String message, String userMessage){
        super(message);
        this.errorCode = "0155";
        this.errDesc = errDesc;
        this.userMessage = userMessage;
    }
}
