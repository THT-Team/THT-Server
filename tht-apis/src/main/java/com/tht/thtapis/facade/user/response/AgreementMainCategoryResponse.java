package com.tht.thtapis.facade.user.response;

public record AgreementMainCategoryResponse(
        String name,
        String subject,
        boolean isRequired,
        String description,
        String detailLink
) {

    public static AgreementMainCategoryResponse of(final String name, final String subject, final boolean isRequired, final String description, final String detailLink) {
        return new AgreementMainCategoryResponse(name, subject, isRequired, description, detailLink);
    }
}
