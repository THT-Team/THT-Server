package com.tht.thtapis.service;

import com.tht.infra.user.UserAlarmAgreement;
import com.tht.infra.user.exception.UserCustomException;
import com.tht.infra.user.repository.UserAlarmAgreementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserAlarmAgreementService {

    private final UserAlarmAgreementRepository repository;

    public void create(final String userUuid) {
        repository.save(UserAlarmAgreement.create(userUuid));
    }

    public void update(final String userUuid, final boolean newMatchSuccessAlarm,
        final boolean likeMeAlarm, final boolean newConversationAlarm, final boolean talkAlarm) {

        UserAlarmAgreement userAlarmAgreement = repository.findByUserUuid(userUuid)
            .orElseThrow(() -> UserCustomException.notExistAlarmInfo(userUuid));

        userAlarmAgreement.modify(newMatchSuccessAlarm, likeMeAlarm, newConversationAlarm,
            talkAlarm);
    }
}
