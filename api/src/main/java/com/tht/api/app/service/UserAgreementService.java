package com.tht.api.app.service;

import com.tht.api.app.entity.user.UserAgreement;
import com.tht.api.app.repository.UserAgreementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserAgreementService {

    private final UserAgreementRepository userAgreementRepository;

    public UserAgreement create(final UserAgreement userAgreement) {
        return userAgreementRepository.save(userAgreement);
    }
}
