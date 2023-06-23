package com.tht.api.app.controller;

import com.tht.api.app.entity.user.User;
import com.tht.api.app.facade.user.UserFacade;
import com.tht.api.app.facade.user.request.MainScreenUserInfoRequest;
import com.tht.api.app.facade.user.response.MainScreenUserInfoResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;
    @GetMapping("/main/daily-falling/users")
    public ResponseEntity<List<MainScreenUserInfoResponse>> getMainScreenUserList(
        @AuthenticationPrincipal final User user,
        @Valid final MainScreenUserInfoRequest request
    ) {

        return ResponseEntity.ok(userFacade.findAllToDayFallingUserList(user.getUserUuid(), request));
    }
}
