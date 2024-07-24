package com.tht.thtapis.ui;

import com.tht.domain.entity.user.User;
import com.tht.thtapis.facade.setting.InquiryRequest;
import com.tht.thtapis.facade.setting.SettingFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SettingController {

    private final SettingFacade settingFacade;

    @PostMapping("/user/inquiry")
    public ResponseEntity<Object> inquiry(@AuthenticationPrincipal User user,
                                          @RequestBody @Valid InquiryRequest request) {

        settingFacade.inquiry(user.getUserUuid(), request);
        return ResponseEntity.ok().build();
    }
}
