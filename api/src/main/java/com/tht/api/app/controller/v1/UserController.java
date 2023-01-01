package com.tht.api.app.controller.v1;

import com.tht.api.app.entity.User;
import com.tht.api.app.facade.request.UserSignUpRequestDTO;
import com.tht.api.app.facade.respone.UserResponseDTO;
import com.tht.api.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<User> signUp(@RequestBody UserSignUpRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signup(request));
    }
}