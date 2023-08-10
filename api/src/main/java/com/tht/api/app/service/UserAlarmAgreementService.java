package com.tht.api.app.service;

import com.tht.api.app.entity.user.UserAlarmAgreement;
import com.tht.api.app.repository.user.UserAlarmAgreementRepository;
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
}
