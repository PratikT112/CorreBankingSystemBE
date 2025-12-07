package com.pratikt112.correbankingsystembe.exception;

public class RecordNotFoundException extends BankingSystemException {
    public RecordNotFoundException(String errorCode, String message, String userMessage) {
        super(errorCode, message, userMessage);
    }

    public RecordNotFoundException(String recordType, String identifier){
        super("RECORD_NOT_FOUND",
                recordType + "Record not found with key: " + identifier,
                "No record found with this information. Please check key and try again");
    }
}
