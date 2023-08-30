package com.example.githubrepoapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFound(ResourceNotFoundException exception) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(UnsupportedMediaTypeException.class)
    public ResponseEntity<ExceptionResponse> handleUnsupportedMediaType(UnsupportedMediaTypeException exception) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_ACCEPTABLE.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
