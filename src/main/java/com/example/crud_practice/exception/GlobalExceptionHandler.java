package com.example.crud_practice.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.net.URI;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ExceptionResponse> handleIOException(IOException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionResponse.makeExceptionDto(
                HttpStatus.BAD_REQUEST, e.getMessage(), URI.create(request.getRequestURI())
        ));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ExceptionResponse.makeExceptionDto(
                HttpStatus.UNAUTHORIZED, e.getMessage(), URI.create(request.getRequestURI())
        ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(MethodArgumentNotValidException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT).body(ExceptionResponse.makeExceptionDto(
                HttpStatus.PERMANENT_REDIRECT, e.getFieldError().getDefaultMessage(), URI.create(request.getRequestURI())
        ));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(NullPointerException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ExceptionResponse.makeExceptionDto(
                HttpStatus.NOT_FOUND, e.getMessage(), URI.create(request.getRequestURI())
        ));
    }
}
