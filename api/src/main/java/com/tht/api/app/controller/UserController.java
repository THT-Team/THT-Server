package com.tht.api.app.controller;

import com.tht.api.app.entity.enums.Gender;
import com.tht.api.app.entity.user.User;
import com.tht.api.app.facade.user.UserFacade;
import com.tht.api.app.facade.user.request.ModifiedIdealTypeRequest;
import com.tht.api.app.facade.user.request.ModifiedInterestsRequest;
import com.tht.api.app.facade.user.request.UserAlarmAgreementModifyRequest;
import com.tht.api.app.facade.user.request.UserLocationRequest;
import com.tht.api.app.facade.user.request.UserModifyProfilePhotoRequest;
import com.tht.api.app.facade.user.request.UserReportRequest;
import com.tht.api.app.facade.user.request.UserWithDrawRequest;
import com.tht.api.app.facade.user.response.UserDetailResponse;
import com.tht.api.app.facade.user.response.UserLoginResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

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

    @PatchMapping("/user/email/{email}")
    public ResponseEntity<Object> updateEmail(
        @PathVariable final String email,
        @AuthenticationPrincipal final User user) {

        userFacade.updateEmail(user, email);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/user/interests")
    public ResponseEntity<Object> modifiedInterests(
        @AuthenticationPrincipal final User user,
        @RequestBody @Valid final ModifiedInterestsRequest request) {

        userFacade.modifiedInterests(user.getUserUuid(), request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/user/ideal-type")
    public ResponseEntity<Object> modifiedIdealType(
        @AuthenticationPrincipal final User user,
        @RequestBody @Valid final ModifiedIdealTypeRequest request) {

        userFacade.modifiedIdealType(user.getUserUuid(), request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/user/location")
    public ResponseEntity<Object> updateMyLocation(
        @AuthenticationPrincipal final User user,
        @RequestBody @Valid final UserLocationRequest request) {

        userFacade.updateLocation(user.getUserUuid(), request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/user/name/{nick-name}")
    public ResponseEntity<UserLoginResponse> updateNickName(
        @AuthenticationPrincipal final User user,
        @PathVariable(name = "nick-name") final String updateNickName) {

        return ResponseEntity.ok(userFacade.updateNickName(user, updateNickName));
    }

    @PatchMapping("/user/introduction")
    public ResponseEntity<Object> updateSelfIntroduce(
        @AuthenticationPrincipal final User user,
        @RequestBody final String introduction) {

        userFacade.updateIntroduction(user, introduction);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/user/profile-photo")
    public ResponseEntity<Object> updateProfileList(
        @AuthenticationPrincipal final User user,
        @RequestBody @Valid final UserModifyProfilePhotoRequest request) {

        userFacade.updateUserProfilePhoto(user.getUserUuid(), request.userProfilePhotoList());
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/user/preferred-gender/{gender}")
    public ResponseEntity<Object> updatePreferGender(
        @AuthenticationPrincipal final User user,
        @PathVariable final Gender gender) {

        userFacade.updatePreferGender(user, gender);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/user/alarm-agreement")
    public ResponseEntity<Object> updateUserAlarm(
        @AuthenticationPrincipal final User user,
        @RequestBody @Valid final UserAlarmAgreementModifyRequest request) {

        userFacade.updatePersonalAlarmAgree(user.getUserUuid(), request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/user/account-withdrawal")
    public ResponseEntity<Object> withDraw(
        @AuthenticationPrincipal final User user,
        @RequestBody @Valid final UserWithDrawRequest request
    ) {

        userFacade.withDraw(user, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
