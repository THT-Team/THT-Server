package com.tht.api.app.repository.user.querydsl;

import com.tht.api.app.entity.enums.AgreementCategory;

public interface UserAgreementCustomRepository {
    void singleUpdate(final String userUuid, final AgreementCategory agreementCategory, final boolean value);

}
