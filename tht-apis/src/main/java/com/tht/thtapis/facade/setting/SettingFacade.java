package com.tht.thtapis.facade.setting;

import com.tht.domain.entity.inquiry.InquiryService;
import com.tht.thtapis.facade.Facade;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Facade
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SettingFacade {

    private final InquiryService inquiryService;

    @Transactional
    public void inquiry(final String userUuid, final InquiryRequest request) {
        inquiryService.receiptInquiry(userUuid, request.contents(), request.userEmail(), request.isEmailAgree());
    }
}
