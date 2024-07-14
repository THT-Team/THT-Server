package com.tht.infra.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AligoException extends RuntimeException {

    public AligoException(final String message){ super(message);}

    public static AligoException of(final String errorMessage) {
        return new AligoException("알리고 문자 전송 에러\n" + errorMessage);
    }
}
