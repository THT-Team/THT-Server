package com.tht.thtapis.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tht.thtcommonutils.utils.CustomDateFormatUtils;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class ErrorResponse {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final String timestamp;
    private final int status;
    private final String error;
    private final String message;
    private final String path;

    @Builder(access = AccessLevel.PRIVATE)
    private ErrorResponse(final int status, final String error, final String message,
        final String path) {
        this.timestamp = LocalDateTime.now().format(CustomDateFormatUtils.getDateTimeInstance());
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

    public static ErrorResponse of(final int httpStatus, final String reasonParse,
        final String message, final HttpServletRequest httpServletRequest) {
        return ErrorResponse.builder()
            .status(httpStatus)
            .error(reasonParse)
            .message(message)
            .path(httpServletRequest.getRequestURI())
            .build();
    }

    public String convertToJson() throws JsonProcessingException {
        return objectMapper.writeValueAsString(this);
    }

}
