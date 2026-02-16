package com.pratikt112.correbankingsystembe.exception;

public class UserNotFoundException extends BankingSystemException {

    public UserNotFoundException(String errorCode, String message, String userMessage) {
        super(errorCode, message, userMessage);
    }

    public UserNotFoundException(String tellerNo){
        super("USER_NOT_FOUND",
                "User not found with ID: " + tellerNo,
                "User not found. Please verify the Teller no and try again");
    }
}
