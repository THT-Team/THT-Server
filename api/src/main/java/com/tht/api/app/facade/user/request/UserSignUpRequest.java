package com.tht.api.app.facade.user.request;

import com.tht.api.app.config.utils.CustomDateFormatUtils;
import com.tht.api.app.entity.enums.Gender;
import com.tht.api.app.entity.user.User;
import com.tht.api.app.entity.user.UserAgreement;
import com.tht.api.app.entity.user.UserDeviceKey;
import com.tht.api.app.entity.user.UserIdealType;
import com.tht.api.app.entity.user.UserInterests;
import com.tht.api.app.entity.user.UserLocationInfo;
import com.tht.api.app.entity.user.UserProfilePhoto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

public record UserSignUpRequest (

    @NotBlank(message = "phoneNumber 는 비어있을 수 없습니다.") String phoneNumber,
    @NotBlank(message = "username 는 비어있을 수 없습니다.") String username,
    @NotBlank(message = "email 는 비어있을 수 없습니다.") String email,
    String birthDay,
    Gender gender,
    Gender preferGender,
    @NotNull(message = "introduction 는 null 이어서는 안됩니다.") String introduction,
    @NotBlank(message = "deviceKey 는 비어있을 수 없습니다.") String deviceKey,
    @NotNull(message = "agreement 는 null 이어서는 안됩니다.") @Valid UserAgreementRequest agreement,
    @NotNull(message = "locationRequest 는 null 이어서는 안됩니다.") @Valid UserLocationRequest locationRequest,
    @Size(min = 2, max = 3, message = "사진은 최소 2장, 최대 3장을 등록해야합니다.")
    @NotNull(message = "photoList 는 null 이어서는 안됩니다.")
    List<String> photoList,

    @Size(max = 3, message = "관심사는 최대 3개를 골라주세요")
    @NotNull(message = "interestList 는 null 이어서는 안됩니다.")
    List<Long> interestList,

    @Size(max = 3, message = "이상형은 최대 3개를 골라주세요")
    @NotNull(message = "idealTypeList 는 null 이어서는 안됩니다.")
    List<Long> idealTypeList
){

    public UserSignUpRequest(final String phoneNumber, final String username, final String email,
        final String birthDay, final String gender, final String preferGender,
        final String introduction, final String deviceKey,
        final UserAgreementRequest agreement, final UserLocationRequest locationRequest,
        final List<String> photoList, final List<Long> interestList,
        final List<Long> idealTypeList) {

        this(phoneNumber,
            username,
            email,
            birthDay,
            Gender.toConverter(gender),
            Gender.toConverter(preferGender),
            introduction,
            deviceKey,
            agreement,
            locationRequest,
            photoList,
            interestList,
            idealTypeList);

    }

    public User toEntity() {
        return User.createNewUser(username, getBirthDayConvertLocalDate(), phoneNumber,
            email, introduction, gender, preferGender);
    }

    private LocalDate getBirthDayConvertLocalDate() {
        return LocalDate.parse(birthDay, CustomDateFormatUtils.getDotDateInstance());
    }

    public UserAgreement makeAgreementToEntity(final String userUuid) {
        return this.agreement.toEntity(userUuid);
    }

    public UserLocationInfo makeUserLocationToEntity(final String userUuid) {
        return this.locationRequest.toEntity(userUuid);
    }

    public List<UserProfilePhoto> makeUserProfilePhotoList(final String userUuid) {
        return photoList.stream()
            .map(s -> UserProfilePhoto.create(userUuid, s, photoList.indexOf(s) + 1))
            .toList();
    }

    public List<UserInterests> makeUserInterestsList(final String userUuid) {
        return interestList.stream().map(idx -> UserInterests.create(userUuid, idx))
            .toList();
    }

    public List<UserIdealType> makeUserIdealTypeList(final String userUuid) {
        return idealTypeList.stream().map(idx -> UserIdealType.create(userUuid, idx))
            .toList();
    }

    public UserDeviceKey makeDeviceKeyToEntity(final String userUuid) {
        return UserDeviceKey.create(userUuid, deviceKey);
    }
}
