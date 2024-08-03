package com.tht.domain.entity.inquiry;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InquiryService {

    private final InquiryRepository repository;

    @Transactional
    public void receiptInquiry(final String userUuid, final String contents, final String userEmail, final boolean isEmailAgree) {
        final UserInquiry userInquiry = UserInquiry.createInquiry(userUuid, contents, userEmail, isEmailAgree);
        repository.save(userInquiry);
    }

    @Transactional
    public void beforeLogin(final String contents, final String userEmail, final boolean emailAgree) {
        final UserInquiry anonymous = UserInquiry.createForAnonymous(contents, userEmail, emailAgree);
        repository.save(anonymous);
    }
}
