package com.tht.api.app.controller;

import com.tht.api.app.facade.user.UserResponse;
import com.tht.api.app.facade.user.response.UserFacade;
import com.tht.api.app.facade.user.request.UserSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserFacade userFacade;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserSignUpRequest request) {
        request.validate();
        UserResponse user = userFacade.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
