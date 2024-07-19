package com.tht.infra.user.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tht.enums.agreement.AgreementCategory;
import com.tht.infra.user.QUserAgreement;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserAgreementCustomRepositoryImpl implements UserAgreementCustomRepository {

    private final JPAQueryFactory queryFactory;

    private QUserAgreement userAgreement = QUserAgreement.userAgreement;

    @Override
    public void singleUpdate(String userUuid, AgreementCategory agreementCategory, boolean value) {

        queryFactory.update(userAgreement)
                .set(getAgree(agreementCategory), value)
                .where(userAgreement.userUuid.eq(userUuid))
                .execute();
    }

    private BooleanPath getAgree(AgreementCategory agreementCategory) {

        if(agreementCategory.equals(AgreementCategory.SERVICE_USE_AGREE)){
            return userAgreement.serviceUseAgree;
        }
        if (agreementCategory.equals(AgreementCategory.MARKETING_AGREE)) {
            return userAgreement.marketingAgree;
        }
        if (agreementCategory.equals(AgreementCategory.LOCATION_SERVICE_AGREE)) {
            return userAgreement.locationServiceAgree;
        }
        if (agreementCategory.equals(AgreementCategory.PERSONAL_PRIVACY_INFO_AGREE)) {
            return userAgreement.personalPrivacyInfoAgree;
        }

        return null;
    }
}
