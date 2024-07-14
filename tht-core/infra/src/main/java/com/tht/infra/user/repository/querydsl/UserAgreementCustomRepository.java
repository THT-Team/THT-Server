package com.tht.infra.user.repository.querydsl;


import com.tht.infra.agreement.AgreementCategory;

public interface UserAgreementCustomRepository {
    void singleUpdate(final String userUuid, final AgreementCategory agreementCategory, final boolean value);

}
