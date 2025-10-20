package com.pratikt112.correbankingsystembe.exception;

public class IncompleteDataException extends BankingSystemException{
    public IncompleteDataException(String errorCode, String message, String userMessage) {
        super(errorCode, message, userMessage);
    }

    public IncompleteDataException(String errorCode, String message, String userMessage, Throwable cause) {
        super(errorCode, message, userMessage, cause);
    }

    public IncompleteDataException(String EntityName, String fieldName) {
        super("INCOMPLETE_DATA_EXCEPTION",
                "Value not provided in field: " + fieldName + " of Entity: " + EntityName,
                "Value is not provided in one or more fields. Please check and try again");
    }
}
