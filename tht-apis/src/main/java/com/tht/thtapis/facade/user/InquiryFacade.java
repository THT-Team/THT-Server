package com.tht.thtapis.facade.user;

import com.tht.domain.entity.inquiry.InquiryService;
import com.tht.thtapis.facade.Facade;
import com.tht.thtapis.facade.setting.InquiryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Facade
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InquiryFacade {

    private final InquiryService inquiryService;

    @Transactional
    public void inquiryForLogin(final InquiryRequest request) {
        inquiryService.beforeLogin(request.contents(), request.userEmail(), request.isEmailAgree());
    }

}
