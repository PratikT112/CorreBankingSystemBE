package com.pratikt112.correbankingsystembe.exception;

import com.pratikt112.correbankingsystembe.DTOs.ErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ReportAsSingleViolation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.stream.Collectors;


/**
 * Global exception handler for the banking system
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /*
    * Handle custom banking system exceptions
    */
    @ExceptionHandler(BankingSystemException.class)
    public ResponseEntity<ErrorResponse> handleBankingSystemException(BankingSystemException ex, WebRequest request){
        logger.error("Banking system exception occurred: {}", ex.getMessage(), ex);

        HttpStatus status = determineHttpStatus(ex);
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getErrorCode(),
                ex.getMessage(),
                ex.getUserMessage(),
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex, WebRequest request){
        logger.warn("Validation exception occurred: {}", ex.getMessage());

        String validationErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        ErrorResponse errorResponse = new ErrorResponse(
                "VALIDATION_ERROR",
                "Validation failed: " + validationErrors,
                "Please check your input data and try again.",
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /*
    * Handle constraint violation exceptions
    * */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request){
        logger.warn("Constraint violation exception occurred: {}", ex.getMessage());

        String violations = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));

        ErrorResponse errorResponse = new ErrorResponse(
                "CONSTRAINT_VIOLATION",
                "Constraint violation:" + violations,
                "Please check your input data and try again.",
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    /*
    * Handle data integrity violation exceptions
    * */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request){
        logger.error("Data integrity violation occurred: {}", ex.getMessage(), ex);

        ErrorResponse errorResponse = new ErrorResponse(
                "DATA_INTEGRITY_VIOLATION",
                "Database constraint violation: " + ex.getMostSpecificCause().getMessage(),
                "The operation violated database constraints. Please check your data and try again.",
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }


    /*
    * Handle Illegal argument exceptions
    * */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request){
        logger.warn("Illegal argument exception occurred: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                "INVALID_ARGUMENT",
                ex.getMessage(),
                "Invalid input provided. Please check you data and try again.",
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /*
    * Handle JSON parsing exceptions
    * */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request){
        logger.warn("JSON parsing exception occurred: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                "INVALID_JSON",
                "Invalid JSON format in request body",
                "Please check the JSON format of your request and try again",
                request.getDescription(false).replace("uri=", "")

        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateRecordException(DuplicateRecordException ex, WebRequest request){
        logger.error("Duplicate record exception occurred: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                "DUPLICATE_RECORD_FOUND",
                ex.getMessage(),
                ex.getUserMessage(),
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IncompleteDataException.class)
    public ResponseEntity<ErrorResponse> handleIncompleteDataException(IncompleteDataException ex, WebRequest request){
        logger.error("Incomplete data exception occurred: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                "INCOMPLETE_DATA_PROVIDED",
                ex.getMessage(),
                ex.getUserMessage(),
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.PARTIAL_CONTENT);
    }

    /*
    * Handle method not supported exceptions
    * */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, WebRequest request){
        logger.warn("Method not supported exception occurred: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                "METHOD_NOT_SUPPORTED",
                "HTTP method not supported: " + ex.getMethod(),
                "The requested HTTP method is not supported for this endpoint.",
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /*
    *
    * */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex, WebRequest request){
        logger.warn("Missing parameter exception occurred: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                "MISSING_PARAMETER",
                "Required parameter missing: " + ex.getParameterName(),
                "Please provide all required parameters and try again.",
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }



    private HttpStatus determineHttpStatus(BankingSystemException ex){
        return switch(ex.getErrorCode()){
            case "CUSTOMER_NOT_FOUND" -> HttpStatus.NOT_FOUND;
            case "DUPLICATE_RECORD" -> HttpStatus.CONFLICT;
            case "VALIDATION_ERROR" -> HttpStatus.BAD_REQUEST;
            case "DATABASE_ERROR" -> HttpStatus.INTERNAL_SERVER_ERROR;
            case "CIF_GENERATION_ERROR" -> HttpStatus.INTERNAL_SERVER_ERROR;
            case "PROCESSING_ERROR" -> HttpStatus.INTERNAL_SERVER_ERROR;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }

}