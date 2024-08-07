package com.tht.thtadmin.exception;

import com.tht.domain.exception.EntityStateException;
import com.tht.enums.EnumStateNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.format.DateTimeParseException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerException(final IllegalArgumentException e,
                                                          final HttpServletRequest request) {
        return ResponseEntity.badRequest()
            .body(ErrorResponse.of(BAD_REQUEST, e.getMessage(), request));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerException(final MethodArgumentNotValidException e,
        final HttpServletRequest request) {

        return ResponseEntity.badRequest().body(
            ErrorResponse.of(BAD_REQUEST,
                e.getBindingResult().getAllErrors().get(0).getDefaultMessage(), request)
        );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerException(final DateTimeParseException e,
        final HttpServletRequest request) {

        return ResponseEntity.badRequest().body(
            ErrorResponse.of(BAD_REQUEST, e.getMessage(), request)
        );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerException(final HttpMessageNotReadableException e,
        final HttpServletRequest request) {

        return ResponseEntity.badRequest().body(
            ErrorResponse.of(BAD_REQUEST, e.getCause().getCause().getMessage(), request)
        );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerException(
        final HttpRequestMethodNotSupportedException e,
        final HttpServletRequest request) {

        return ResponseEntity.badRequest().body(
            ErrorResponse.of(NOT_FOUND, e.getMessage(), request)
        );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerException(final EnumStateNotFoundException e,
        final HttpServletRequest request) {

        return ResponseEntity.badRequest().body(
            ErrorResponse.of(BAD_REQUEST, e.getMessage(), request)
        );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerException(final BadCredentialsException e,
        final HttpServletRequest request) {

        return ResponseEntity.badRequest().body(
            ErrorResponse.of(HttpStatus.UNAUTHORIZED, e.getMessage(), request)
        );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerException(final NoHandlerFoundException e,
        final HttpServletRequest request) {

        return ResponseEntity.badRequest().body(
            ErrorResponse.of(NOT_FOUND, e.getMessage(), request)
        );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerException(final EntityStateException e,
                                                          final HttpServletRequest request) {

        return ResponseEntity.badRequest().body(
            ErrorResponse.of(BAD_REQUEST, e.getMessage(), request)
        );
    }
}