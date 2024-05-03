package com.tht.api.app.facade.user.request;

public record UserAgreementUpdateRequest(
        String agreementName,
        boolean value
)  {
}
