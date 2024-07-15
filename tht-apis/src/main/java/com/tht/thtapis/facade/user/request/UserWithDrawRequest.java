package com.tht.thtapis.facade.user.request;

import jakarta.validation.constraints.NotEmpty;

public record UserWithDrawRequest(
    @NotEmpty(message = "틸퇴 사유를 입력해주세요") String reason,
    @NotEmpty(message = "피드백을 입력해주세요") String feedBack
) {

}
