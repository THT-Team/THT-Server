package com.tht.domain.entity.user.service;

import com.tht.enums.agreement.AgreementCategory;
import com.tht.domain.entity.user.UserAgreement;
import com.tht.domain.entity.user.exception.UserCustomException;
import com.tht.domain.entity.user.repository.UserAgreementRepository;
import com.tht.thtcommonutils.utils.LogWriteUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserAgreementService {

    private final UserAgreementRepository userAgreementRepository;

    public UserAgreement create(final UserAgreement userAgreement) {
        LogWriteUtils.logInfo("new_user_agreement_info : " + userAgreement);
        return userAgreementRepository.save(userAgreement);
    }

    public void modifyMarketingAgree(final String userUuid, final boolean marketingAlarm) {

        UserAgreement userAgreement = userAgreementRepository.findByUserUuid(userUuid)
            .orElseThrow(() -> UserCustomException.notExistAlarmInfo(userUuid));

        userAgreement.modifyMarketingAgree(marketingAlarm);
    }

    public UserAgreement findByUserUuid(final String userUuid) {
        return userAgreementRepository.findByUserUuid(userUuid)
                .orElseThrow(
                        () -> UserCustomException.notExistUserAgreementsInfo(userUuid)
                );
    }

    public void updateSingleAgreement(final String userUuid, final AgreementCategory agreementCategory, final boolean value) {

        userAgreementRepository.singleUpdate(userUuid, agreementCategory, value);
    }
}
