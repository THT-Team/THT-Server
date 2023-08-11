package com.tht.api.app.facade.user.request;

import jakarta.validation.constraints.NotEmpty;

public record UserWithDrawRequest(
    @NotEmpty String reason,
    @NotEmpty String feedBack
) {

}
