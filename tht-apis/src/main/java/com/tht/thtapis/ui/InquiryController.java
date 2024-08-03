package com.tht.thtapis.ui;

import com.tht.thtapis.facade.setting.InquiryRequest;
import com.tht.thtapis.facade.user.InquiryFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InquiryController {

    private final InquiryFacade inquiryFacade;

    @PostMapping("/users/login/inquiry")
    public ResponseEntity<Object> inquiryUserJoin(@RequestBody @Valid InquiryRequest request) {

        inquiryFacade.inquiryForLogin(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
