package com.tht.thtapis.facade.setting;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;

public record InquiryRequest(
    @NotEmpty String userEmail,
    @NotEmpty String contents,
    @AssertTrue boolean isEmailAgree ) {
}
