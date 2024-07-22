package com.tht.thtadmin.security;

import io.jsonwebtoken.JwtException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TokenNotValidateException extends JwtException {

    private HttpStatus status;
    private String reasonParse;

    public TokenNotValidateException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
        this.reasonParse = status.getReasonPhrase();
    }

    public TokenNotValidateException(String message, Throwable cause, HttpStatus status,
        String reasonParse) {
        super(message, cause);
        this.status = status;
        this.reasonParse = reasonParse;
    }

    public static TokenNotValidateException InvalidSecuritySign(final Throwable cause) {
        return new TokenNotValidateException("잘못된 JWT 서명입니다.", cause, HttpStatus.UNAUTHORIZED);
    }

    public static TokenNotValidateException UnsupportedJwt(final Throwable cause) {
        return new TokenNotValidateException("지원되지 않는 JWT 토큰입니다.", cause, HttpStatus.UNAUTHORIZED);
    }

    public static TokenNotValidateException IllegalArgumentJwt(final Throwable cause) {
        return new TokenNotValidateException("JWT 토큰이 잘못되었습니다.", cause, HttpStatus.UNAUTHORIZED);
    }

    public static TokenNotValidateException ExpiredToken(final Throwable cause) {
        return new TokenNotValidateException("만료된 JWT 토큰입니다.", cause, HttpStatus.UNAUTHORIZED, "access_token_expired");
    }

    public int getStatusValue() {
        return status.value();
    }

}