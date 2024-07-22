package com.tht.thtadmin.ui.login;

import com.tht.thtadmin.security.TokenDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginUseCase loginUseCase;

    @PostMapping("/login")
    ResponseEntity<TokenDto> login(@RequestBody @Valid LoginRequest request) {
        final TokenDto token = loginUseCase.login(request.id(), request.password());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/create")
    ResponseEntity<TokenDto> create(@RequestBody @Valid SignUpRequest request) {
        final TokenDto tokenDto = loginUseCase.create(request.id(), request.password(), request.username());
        return ResponseEntity.status(HttpStatus.CREATED)
            .location(URI.create("/login"))
            .body(tokenDto);
    }

}
