package com.tht.thtadmin.ui.login;

import com.tht.thtadmin.security.TokenDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginUseCase loginUseCase;

    @PostMapping("/login")
    ResponseEntity<TokenDto> login(@RequestBody @Valid LoginRequest request) {
        final TokenDto token = loginUseCase.login(request.id(), request.password());
        return ResponseEntity.ok(token);
    }
}
