package com.tht.api.app.facade.user.request;

import jakarta.validation.constraints.NotEmpty;

public record UserLoginRequest(
    @NotEmpty String phoneNumber
) { }
