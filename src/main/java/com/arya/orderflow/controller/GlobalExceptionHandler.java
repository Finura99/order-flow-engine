package com.arya.orderflow.controller;

import com.arya.orderflow.exception.PortfolioNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // this class handles api exceptions globally

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalException( IllegalArgumentException exception) {
        // method runs when this exception type escapes

        return ResponseEntity.badRequest().body(exception.getMessage());
        // return http 400 series
    }

    @ExceptionHandler(PortfolioNotFoundException.class)
    public ResponseEntity<String> handlePortfolioNotFound(PortfolioNotFoundException exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

}