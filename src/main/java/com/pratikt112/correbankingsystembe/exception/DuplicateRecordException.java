package com.pratikt112.correbankingsystembe.exception;

public class DuplicateRecordException extends BankingSystemException{
    public DuplicateRecordException(String errorCode, String message, String userMessage) {
        super(errorCode, message, userMessage);
    }

    public DuplicateRecordException(String errorCode, String message, String userMessage, Throwable cause) {
        super(errorCode, message, userMessage, cause);
    }

    public DuplicateRecordException(String recordType, String identifier) {
        super("DUPLICATE_RECORD",
                "Duplicate " + recordType + " record found with identifier: " + identifier,
                "A record with this information already exists. Please check your data and try again.");
    }

    public DuplicateRecordException(String recordType, String identifier, Throwable cause) {
        super("DUPLICATE_RECORD",
                "Duplicate " + recordType + " record found with identifier: " + identifier,
                "A record with this information already exists. Please check your data and try again.",
                cause);
    }
}
