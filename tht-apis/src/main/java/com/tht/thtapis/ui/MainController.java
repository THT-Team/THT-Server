package com.tht.thtapis.ui;

import com.tht.infra.user.User;
import com.tht.thtapis.facade.user.UserFacade;
import com.tht.thtapis.facade.user.request.MainScreenUserInfoRequest;
import com.tht.thtapis.facade.user.response.MainScreenResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final UserFacade userFacade;

    @PostMapping("/main/daily-falling/users")
    public ResponseEntity<MainScreenResponse> getMainScreenUserList(
        @AuthenticationPrincipal final User user,
        @RequestBody @Valid final MainScreenUserInfoRequest request) {

        return ResponseEntity.ok(
            userFacade.findAllToDayFallingUserList(user, request));
    }
}
