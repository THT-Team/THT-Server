package com.tht.api.app.controller;

import com.tht.api.app.entity.user.User;
import com.tht.api.app.facade.user.UserFacade;
import com.tht.api.app.facade.user.request.MainScreenUserInfoRequest;
import com.tht.api.app.facade.user.request.UserReportRequest;
import com.tht.api.app.facade.user.response.MainScreenResponse;
import com.tht.api.app.facade.user.response.UserDetailResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @PostMapping("/main/daily-falling/users")
    public ResponseEntity<MainScreenResponse> getMainScreenUserList(
        @AuthenticationPrincipal final User user,
        @RequestBody @Valid final MainScreenUserInfoRequest request) {

        return ResponseEntity.ok(
            userFacade.findAllToDayFallingUserList(user.getUserUuid(), request));
    }

    @PostMapping("/user/report")
    public ResponseEntity<Object> reportUser(
        @AuthenticationPrincipal final User user,
        @RequestBody @Valid final UserReportRequest request
    ) {

        userFacade.report(user.getUserUuid(), request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user/block/{block-user-uuid}")
    public ResponseEntity<Object> blockUser(
        @AuthenticationPrincipal final User user,
        @PathVariable(name = "block-user-uuid") final String blockUserUuid
    ) {

        userFacade.block(user.getUserUuid(), blockUserUuid);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user")
    public ResponseEntity<UserDetailResponse> getUserDetail(
        @AuthenticationPrincipal final User user) {

        return ResponseEntity.ok(userFacade.getUserDetail(user));
    }

    @PatchMapping("/user/phone-number/{phone-number}")
    public ResponseEntity<Object> updatePhoneNumber(
        @PathVariable(name = "phone-number") final String phoneNumber,
        @AuthenticationPrincipal final User user) {

        userFacade.updatePhoneNumber(user, phoneNumber);
        return ResponseEntity.ok().build();
    }
}
