package com.pratikt112.correbankingsystembe.exception;

public class CifGenerationException extends BankingSystemException{

    public CifGenerationException(String errorCode, String message, String userMessage) {
        super(errorCode, message, userMessage);
    }

    public CifGenerationException(String errorCode, String message, String userMessage, Throwable cause) {
        super(errorCode, message, userMessage, cause);
    }

    public CifGenerationException(String message){
        super("CIF_GENERATION_ERROR",
                "Failed to generate CIF: " + message,
                "Unable to generate CIF no. Please try again later.");
    }

    public CifGenerationException(String message, Throwable cause){
        super("CIF_GENERATION_ERROR",
                "Failed to generate CIF: " + message,
                "Unable to generate CIF no. Please try again later.",
                cause);
    }
}
