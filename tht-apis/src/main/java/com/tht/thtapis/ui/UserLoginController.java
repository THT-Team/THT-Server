package com.tht.thtapis.ui;

import com.tht.thtapis.security.TokenDto;
import com.tht.thtapis.security.SecurityConst;
import com.tht.thtapis.usecase.login.UserLoginRequest;
import com.tht.thtapis.usecase.login.UserSNSLoginRequest;
import com.tht.thtapis.usecase.login.LoginUseCase;
import jakarta.servlet.http.HttpServletRequest;
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

    private final LoginUseCase loginUseCase;

    @PostMapping("/normal")
    public ResponseEntity<TokenDto> normalLogin(
        @RequestBody @Valid UserLoginRequest request) {
        return ResponseEntity.ok(loginUseCase.login(request));
    }

    @PostMapping("/sns")
    public ResponseEntity<TokenDto> snsLogin(
        @RequestBody @Valid UserSNSLoginRequest request) {

        return ResponseEntity.ok(loginUseCase.snsLogin(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenDto> tokenRefresh(final HttpServletRequest request) {

        return ResponseEntity.ok(loginUseCase.refresh(request.getHeader(SecurityConst.AUTH_HEADER_NAME)));
    }
}
