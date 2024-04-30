package com.tht.api.app.facade.user.response;

public record AgreementMainCategoryResponse(
        String name,
        String subject,
        boolean isRequired,
        String description
) {

    public static AgreementMainCategoryResponse of(final String name, final String subject, final boolean isRequired, final String description) {
        return new AgreementMainCategoryResponse(name, subject, isRequired, description);
    }
}
