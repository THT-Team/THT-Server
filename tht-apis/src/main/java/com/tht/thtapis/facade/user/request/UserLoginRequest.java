package com.tht.thtapis.facade.user.request;

import jakarta.validation.constraints.NotEmpty;

public record UserLoginRequest(
    @NotEmpty(message = "phoneNumber 를 입력해주세요.") String phoneNumber,
    @NotEmpty(message = "deviceKey 를 입력해주세요.") String deviceKey

) { }
