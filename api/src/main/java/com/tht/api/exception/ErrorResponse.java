package com.tht.api.exception;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {
    private final LocalDateTime timestamp;
    private final int status;
    private final String error;
    private final String message;
    private final String path;

    @Builder(access = AccessLevel.PRIVATE)
    private ErrorResponse(final int status, final String error, final String message,
        final String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public static ErrorResponse of(final HttpStatus httpStatus, final String message,
        final HttpServletRequest httpServletRequest) {
        return ErrorResponse.builder()
            .status(httpStatus.value())
            .error(httpStatus.getReasonPhrase())
            .message(message)
            .path(httpServletRequest.getRequestURI())
            .build();
    }

}
