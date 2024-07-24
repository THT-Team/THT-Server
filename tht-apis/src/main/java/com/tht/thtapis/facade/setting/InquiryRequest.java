package com.tht.thtapis.facade.setting;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;

public record InquiryRequest(
    @NotEmpty(message = "userEmail 을 입력해주세요") String userEmail,
    @NotEmpty(message = "contents 을 입력해주세요") String contents,
    @AssertTrue(message = "정보제공에 동의해주세요") boolean isEmailAgree
) {
}
