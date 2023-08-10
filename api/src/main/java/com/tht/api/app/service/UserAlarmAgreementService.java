package com.tht.api.app.service;

import com.tht.api.app.entity.user.UserAlarmAgreement;
import com.tht.api.app.repository.user.UserAlarmAgreementRepository;
import com.tht.api.exception.custom.UserCustomException;
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
