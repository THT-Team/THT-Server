package com.tht.api.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.fasterxml.jackson.core.JsonParseException;
import com.tht.api.exception.custom.AligoException;
import com.tht.api.exception.custom.EntityStateException;
import com.tht.api.exception.custom.EnumStateNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import java.time.format.DateTimeParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerException(final MethodArgumentNotValidException e, final HttpServletRequest request) {

        return ResponseEntity.badRequest().body(
            ErrorResponse.of(BAD_REQUEST,
                e.getBindingResult().getAllErrors().get(0).getDefaultMessage(), request)
        );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerException(final DateTimeParseException e, final HttpServletRequest request) {

        return ResponseEntity.badRequest().body(
            ErrorResponse.of(BAD_REQUEST,e.getMessage(), request)
        );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerException(final HttpMessageNotReadableException e, final HttpServletRequest request) {

        return ResponseEntity.badRequest().body(
            ErrorResponse.of(BAD_REQUEST,e.getCause().getCause().getMessage(), request)
        );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerException(final EntityStateException e, final HttpServletRequest request) {

        return ResponseEntity.badRequest().body(
            ErrorResponse.of(BAD_REQUEST,e.getMessage(), request)
        );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerException(final EnumStateNotFoundException e, final HttpServletRequest request) {

        return ResponseEntity.badRequest().body(
            ErrorResponse.of(BAD_REQUEST,e.getMessage(), request)
        );
    }

}
