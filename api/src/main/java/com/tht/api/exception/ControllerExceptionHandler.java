package com.tht.api.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.tht.api.exception.custom.AligoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerException(final IllegalArgumentException e, final HttpServletRequest request) {
        return ResponseEntity.badRequest()
            .body(ErrorResponse.of(BAD_REQUEST, e.getMessage(), request));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerException(final AligoException e, final HttpServletRequest request) {
        return ResponseEntity.badRequest()
            .body(ErrorResponse.of(BAD_REQUEST, e.getMessage(), request));
    }
}
