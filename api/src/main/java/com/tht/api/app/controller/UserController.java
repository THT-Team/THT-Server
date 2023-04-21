package com.tht.api.app.controller;

import com.tht.api.app.facade.user.response.AuthNumberResponse;
import com.tht.api.app.facade.user.response.UserNickNameValidResponse;
import com.tht.api.app.facade.user.response.UserResponse;
import com.tht.api.app.facade.user.UserFacade;
import com.tht.api.app.facade.user.request.UserSignUpRequest;
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
@RequestMapping("/user")
public class UserController {

    private final UserFacade userFacade;

    @GetMapping("/certification/phone-number/{phone-number}")
    ResponseEntity<AuthNumberResponse> getCertificationNumber(
        @PathVariable(name = "phone-number") String phoneNumber) {

        return ResponseEntity.ok(userFacade.issueAuthenticationNumber(phoneNumber));
    }

    @GetMapping("/nick-name/duplicate-check/{nick-name}")
    ResponseEntity<UserNickNameValidResponse> validDuplicateNickname(@PathVariable(name = "nick-name") final String nickName) {
        return ResponseEntity.ok(userFacade.checkDuplicateNickName(nickName));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserSignUpRequest request) {
        request.validate();
        UserResponse user = userFacade.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

}