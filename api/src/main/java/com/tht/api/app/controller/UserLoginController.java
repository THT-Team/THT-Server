package com.tht.api.app.controller;

import com.tht.api.app.facade.user.UserLoginFacade;
import com.tht.api.app.facade.user.request.UserLoginRequest;
import com.tht.api.app.facade.user.request.UserSNSLoginRequest;
import com.tht.api.app.facade.user.response.UserLoginResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users/login")
public class UserLoginController {

    private final UserLoginFacade userLoginFacade;

    @PostMapping("/normal")
    public ResponseEntity<UserLoginResponse> normalLogin(@RequestBody @Valid UserLoginRequest request) {
        return ResponseEntity.ok(userLoginFacade.login(request));
    }

    @PostMapping("/sns")
    public ResponseEntity<UserLoginResponse> snsLogin(
        @RequestBody @Valid UserSNSLoginRequest request) {

        return ResponseEntity.ok(userLoginFacade.snsLogin(request));
    }
}
