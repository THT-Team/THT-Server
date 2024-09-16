package com.tht.thtapis.facade.user.request;

import jakarta.validation.constraints.NotEmpty;

public record UserDeviceKeyRequest(
    @NotEmpty String deviceKey
) {
}
