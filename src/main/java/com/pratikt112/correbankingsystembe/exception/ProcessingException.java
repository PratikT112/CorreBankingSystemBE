package com.pratikt112.correbankingsystembe.exception;

public class ProcessingException extends BankingSystemException{
    public ProcessingException(String errorCode, String message, String userMessage) {
        super(errorCode, message, userMessage);
    }

    public ProcessingException(String errorCode, String message, String userMessage, Throwable cause) {
        super(errorCode, message, userMessage, cause);
    }

    public ProcessingException(String processor, String message) {
        super("PROCESSING_ERROR",
                "Processing failed in " + processor + ": " + message,
                "Customer processing failed. Please try again later.");
    }

    public ProcessingException(String processor, String message, Throwable cause) {
        super("PROCESSING_ERROR",
                "Processing failed in " + processor + ": " + message,
                "Customer processing failed. Please try again later.",
                cause);
    }
}
