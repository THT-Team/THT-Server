package com.tht.thtapis.ui;

import com.tht.domain.entity.user.User;
import com.tht.thtapis.facade.LogoutFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserLogoutController {

    private final LogoutFacade logoutFacade;

    @PostMapping("/user/logout")
    public ResponseEntity<Object> logout(@AuthenticationPrincipal User user) {

        logoutFacade.logout(user);
        return ResponseEntity.ok().build();
    }
}
