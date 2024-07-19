package com.tht.infra.user.repository.querydsl;


import com.tht.enums.agreement.AgreementCategory;

public interface UserAgreementCustomRepository {
    void singleUpdate(final String userUuid, final AgreementCategory agreementCategory, final boolean value);

}
