package com.tht.thtapis.ui;

import com.tht.domain.entity.user.User;
import com.tht.enums.user.Gender;
import com.tht.thtapis.facade.user.UserFacade;
import com.tht.thtapis.facade.user.request.*;
import com.tht.thtapis.facade.user.response.UserDetailResponse;
import com.tht.thtapis.facade.user.response.UserFriendContactResponse;
import com.tht.thtapis.security.TokenDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

        return ResponseEntity.ok(userFacade.getUserDetail(user.getUserUuid()));
    }

    @GetMapping("/user/another/{user-uuid}")
    public ResponseEntity<UserDetailResponse> getAnotherUserDetail(
        @AuthenticationPrincipal final User user,
        @PathVariable(value = "user-uuid") final String userUuid) {

        return ResponseEntity.ok(userFacade.getUserDetail(userUuid));
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
    public ResponseEntity<TokenDto> updateNickName(
        @AuthenticationPrincipal final User user,
        @PathVariable(name = "nick-name") final String updateNickName) {

        return ResponseEntity.ok(userFacade.updateNickName(user, updateNickName));
    }

    @PatchMapping("/user/introduction")
    public ResponseEntity<Object> updateSelfIntroduce(
        @AuthenticationPrincipal final User user,
        @RequestBody final ModifiedIntroductionRequest request) {

        userFacade.updateIntroduction(user, request.introduction());
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

    @GetMapping("/user/friend-contact-list")
    public ResponseEntity<UserFriendContactResponse> getMyFriendListCount(@AuthenticationPrincipal final User user) {

        return ResponseEntity.ok(
            new UserFriendContactResponse(userFacade.getFriendCount(user.getUserUuid()))
        );
    }

    @PostMapping("/user/friend-contact-list")
    public ResponseEntity<UserFriendContactResponse> updateMyFriendContactList(
        @AuthenticationPrincipal final User user,
        @RequestBody @Valid final UserFriendContactRequest request
    ) {

        return ResponseEntity.ok(
            new UserFriendContactResponse(
                userFacade.updateFriendContactList(user.getUserUuid(), request.contacts()))
        );
    }

    @PatchMapping("/user/agreement")
    public ResponseEntity<Object> patchUserAgreement(@AuthenticationPrincipal final User user, @RequestBody @Valid final UserAgreementUpdateRequest request) {

        userFacade.updateSingleUserAgreement(user, request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/user/device-key")
    public ResponseEntity<Object> saveDeviceKey(@AuthenticationPrincipal final User user, @RequestBody @Valid final UserDeviceKeyRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
