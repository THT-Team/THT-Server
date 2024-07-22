package com.tht.thtadmin.ui.login;

import jakarta.validation.constraints.NotEmpty;

public record SignUpRequest(
    @NotEmpty String id,
    @NotEmpty String password,
    @NotEmpty String username
) {
}
