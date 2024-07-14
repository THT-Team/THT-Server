package com.tht.thtapis.facade.user.request;

import com.tht.infra.user.*;
import com.tht.infra.user.enums.Gender;
import com.tht.infra.user.enums.SNSType;
import com.tht.infra.user.enums.UserFrequency;
import com.tht.infra.user.enums.UserReligion;
import com.tht.thtcommonutils.utils.CustomDateFormatUtils;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;

public record UserSignUpRequest(

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
        List<Integer> interestList,

        @Size(max = 3, message = "이상형은 최대 3개를 골라주세요")
        @NotNull(message = "idealTypeList 는 null 이어서는 안됩니다.")
        List<Integer> idealTypeList,

        @NotNull SNSType snsType,

        String snsUniqueId,

        @NotNull(message = "tall 은 null 이어서는 안됩니다.")
        int tall,

        @NotNull(message = "smoking 은 null 이어서는 안됩니다.")
        UserFrequency smoking,

        @NotNull(message = "drinking 은 null 이어서는 안됩니다.")
        UserFrequency drinking,

        @NotNull(message = "religion 은 Null 이어서는 안됩니다.")
        UserReligion religion

        ) {

    private static final String removeHyphenRegex = "[^0-9]";

    public UserSignUpRequest {
        if (snsType.isSns()) {
            validateSnsUniqueId(snsType, snsUniqueId);
        }
    }

    public UserSignUpRequest(final String phoneNumber, final String username, final String email,
                             final String birthDay, final String gender, final String preferGender,
                             final String introduction, final String deviceKey,
                             final UserAgreementRequest agreement, final UserLocationRequest locationRequest,
                             final List<String> photoList, final List<Integer> interestList,
                             final List<Integer> idealTypeList, final String snsType, final String snsUniqueId,
                             final int tall, final String smoking, final String drinking,
                             final String religion) {

        this(phoneNumber.replaceAll(removeHyphenRegex, ""),
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
                idealTypeList,
                SNSType.valueOf(snsType),
                snsUniqueId,
                tall,
                UserFrequency.toConverter(smoking),
                UserFrequency.toConverter(drinking),
                UserReligion.toConverter(religion)
        );
    }

    private void validateSnsUniqueId(final SNSType snsType, final String snsUniqueId) {
        if (!StringUtils.hasText(snsUniqueId)) {
            throw new IllegalArgumentException(snsType.name() + "는 sns unique id 가 필수값입니다.");
        }
    }

    public User toEntity() {
        return User.createNewUser(username, getBirthDayConvertLocalDate(), phoneNumber,
                email, introduction, gender, preferGender, tall, drinking, smoking, religion);
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
}
