package com.tht.api.app.controller;

import com.tht.api.app.facade.join.UserJoinFacade;
import com.tht.api.app.facade.join.response.AuthNumberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserJoinController {

    private final UserJoinFacade userJoinFacade;

    @GetMapping("/user/certification/phone-number/{phone-number}")
    ResponseEntity<AuthNumberResponse> getCertificationNumber(
        @PathVariable(name = "phone-number") String phoneNumber) {

        return ResponseEntity.ok(userJoinFacade.issueAuthenticationNumber(phoneNumber));
    }
}
