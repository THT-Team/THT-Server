package com.tht.api.app.controller;

import com.tht.api.app.facade.user.AgreementFacade;
import com.tht.api.app.facade.user.UserJoinFacade;
import com.tht.api.app.facade.user.response.*;
import com.tht.api.app.facade.user.request.UserSignUpRequest;
import com.tht.api.app.facade.user.request.UserSnsSignUpRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/join")
public class UserJoinController {

    private final UserJoinFacade userJoinFacade;
    private final AgreementFacade agreementFacade;

    @GetMapping("/certification/phone-number/{phone-number}")
    ResponseEntity<AuthNumberResponse> getCertificationNumber(
        @PathVariable(name = "phone-number") String phoneNumber) {

        return ResponseEntity.ok(userJoinFacade.issueAuthenticationNumber(phoneNumber));
    }

    @GetMapping("/nick-name/duplicate-check/{nick-name}")
    ResponseEntity<UserNickNameValidResponse> validDuplicateNickname(
        @PathVariable(name = "nick-name") final String nickName) {
        return ResponseEntity.ok(userJoinFacade.checkDuplicateNickName(nickName));
    }

    @GetMapping("/exist/user-info/{phone-number}")
    ResponseEntity<UserSignUpInfoResponse> getUserSignUpInfo(
        @PathVariable(name = "phone-number") final String phoneNumber) {
        return ResponseEntity.ok(userJoinFacade.getUserSignUpInfo(phoneNumber));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserSignUpResponse> createUser(
        @RequestBody @Valid UserSignUpRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userJoinFacade.signUp(request));
    }

    @PostMapping("/signup/sns")
    public ResponseEntity<UserSignUpResponse> integratedUser(
        @RequestBody @Valid UserSnsSignUpRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userJoinFacade.integratedSnsId(request));
    }

    @GetMapping("/agreements/main-category")
    public ResponseEntity<List<AgreementMainCategoryResponse>> getAgreementMainCategoryList() {

        return ResponseEntity.ok(agreementFacade.getMainCategoryList());
    }
}
