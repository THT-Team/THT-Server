package com.tht.domain.auth;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UnAuthException extends RuntimeException {

    private static final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

    public UnAuthException(final String message) {
        super(message);
    }

    public HttpStatus getHttpStatus(){
        return httpStatus;
    }

}
