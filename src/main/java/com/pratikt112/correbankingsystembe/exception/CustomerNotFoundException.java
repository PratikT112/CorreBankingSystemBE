package com.pratikt112.correbankingsystembe.exception;

import org.hibernate.cache.CacheException;

public class CustomerNotFoundException extends BankingSystemException{
    public CustomerNotFoundException(String errorCode, String message, String userMessage) {
        super(errorCode, message, userMessage);
    }

    public CustomerNotFoundException(String errorCode, String message, String userMessage, Throwable cause) {
        super(errorCode, message, userMessage, cause);
    }

    public CustomerNotFoundException(String custNo){
        super("CUSTOMER_NOT_FOUND",
                "Customer not found with ID: " + custNo,
                "Customer not found. Please verify the customer number and try again");
    }

    public CustomerNotFoundException(String custNo, Throwable cause){
        super("CUSTOMER_NOT_FOUND",
                "Customer not found with ID: " + custNo,
                "Customer not found. Please verify the customer number and try again",
                cause);
    }
}
