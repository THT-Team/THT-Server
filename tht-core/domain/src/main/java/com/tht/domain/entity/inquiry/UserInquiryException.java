package com.tht.domain.entity.inquiry;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserInquiryException extends RuntimeException {

    private HttpStatus httpStatus;

    public UserInquiryException(final String message) {
        super(message);
        httpStatus = HttpStatus.BAD_REQUEST;
    }

    public static UserInquiryException isFalseEmailAgree() {
        return new UserInquiryException("이메일 정보제공에 동의해주세요");
    }
}
