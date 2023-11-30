package com.tht.api.exception.custom;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserTokenException extends RuntimeException {

    private HttpStatus status;
    private String reasonParse;

    public UserTokenException(String message, HttpStatus status) {
        super(message);
        this.status = status;
        this.reasonParse = status.getReasonPhrase();
    }

    public UserTokenException(String message, HttpStatus status, String reasonParse) {
        super(message);
        this.status = status;
        this.reasonParse = reasonParse;
    }

    public static UserTokenException notFoundOfAccessToken() {
        return new UserTokenException("요청 Access Token 에 해당하는 유저 정보를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST);
    }

    public static UserTokenException refreshExpired() {
        return new UserTokenException("refresh token 만료.", HttpStatus.INTERNAL_SERVER_ERROR, "refresh_token_expired");
    }

    public HttpStatus getStatus() {
        return status;
    }

    public int getStatusValue() {
        return status.value();
    }

    public String getReasonParse() {
        return reasonParse;
    }
}
