//package com.pratikt112.correbankingsystembe.exception;
//
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.time.LocalDateTime;
//import java.util.stream.Collectors;
//
////@RestControllerAdvice
//public class GlobalExceptionHandlerGPT {
//
//    public record ErrorResponse(String message, int status, LocalDateTime timestamp) {}
//
//    // Validation errors from @Valid
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
//        String msg = ex.getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .map(e -> e.getField() + ": " + e.getDefaultMessage())
//                .collect(Collectors.joining("; "));
//        return new ResponseEntity<>(new ErrorResponse(msg, HttpStatus.BAD_REQUEST.value(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
//    }
//
//    // JSON parse / type errors
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity<ErrorResponse> handleParse(HttpMessageNotReadableException ex) {
//        String cause = ex.getMostSpecificCause().getMessage();
//        return new ResponseEntity<>(new ErrorResponse("Malformed request: " + cause, HttpStatus.BAD_REQUEST.value(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
//    }
//
//    // Business logic / service exceptions
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<ErrorResponse> handleBusiness(IllegalArgumentException ex) {
//        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
//    }
//
//    // Database exceptions
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponseEntity<ErrorResponse> handleDB(DataIntegrityViolationException ex) {
//        String msg = ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage();
//        return new ResponseEntity<>(new ErrorResponse("Database error: " + msg, HttpStatus.CONFLICT.value(), LocalDateTime.now()), HttpStatus.CONFLICT);
//    }
//
//    // Catch-all
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleAll(Exception ex) {
//        return new ResponseEntity<>(new ErrorResponse("Internal error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now()), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}
