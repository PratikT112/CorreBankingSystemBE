package com.pratikt112.correbankingsystembe.exception;

public class DatabaseException extends BankingSystemException{
    public DatabaseException(String errorCode, String message, String userMessage) {
        super(errorCode, message, userMessage);
    }

    public DatabaseException(String errorCode, String message, String userMessage, Throwable cause) {
        super(errorCode, message, userMessage, cause);
    }

    public DatabaseException(String operation, String message) {
        super("DATABASE_ERROR",
                "Database operation failed during " + operation + ": " + message,
                "A database error occurred. Please try again later.");
    }

    public DatabaseException(String operation, String message, Throwable cause) {
        super("DATABASE_ERROR",
                "Database operation failed during " + operation + ": " + message,
                "A database error occurred. Please try again later.",
                cause);
    }
}
