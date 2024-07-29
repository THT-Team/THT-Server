package com.tht.thtadmin.ui.login.request;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(
    @NotEmpty String id,
    @NotEmpty String password
) { }
