package com.tht.api.app.controller;

import com.tht.api.app.facade.user.UserJoinFacade;
import com.tht.api.app.facade.user.request.UserSignUpInfoResponse;
import com.tht.api.app.facade.user.request.UserSignUpRequest;
import com.tht.api.app.facade.user.response.AuthNumberResponse;
import com.tht.api.app.facade.user.response.UserNickNameValidResponse;
import com.tht.api.app.facade.user.response.UserSignUpResponse;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/join")
public class UserJoinController {

    private final UserJoinFacade userJoinFacade;

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

}
