package com.tht.thtapis.facade.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tht.domain.entity.idealtype.IdealTypeMapper;
import com.tht.domain.entity.interesst.InterestMapper;
import com.tht.enums.user.Gender;
import com.tht.enums.user.UserFrequency;
import com.tht.enums.user.UserReligion;
import com.tht.domain.entity.user.User;
import com.tht.domain.entity.user.UserAgreement;
import com.tht.domain.entity.user.UserLocationInfo;
import com.tht.domain.entity.user.UserProfilePhoto;
import com.tht.thtapis.facade.idealtype.response.IdealTypeResponse;
import com.tht.thtapis.facade.interest.response.InterestResponse;
import com.tht.thtcommonutils.utils.ConvertAgeUtils;

import java.time.LocalDate;
import java.util.List;

public record UserDetailResponse(
        String username,
        String userUuid,
        int age,
        String introduction,
        String address,
        String phoneNumber,
        String email,
        LocalDate birthDay,
        Gender gender,
        @JsonProperty("prefer_gender")
        Gender preferGender,
        int tall,
        UserFrequency smoking,
        UserFrequency drinking,
        UserReligion religion,
        List<IdealTypeResponse> idealTypeList,
        List<InterestResponse> interestsList,
        List<UserProfilePhotoResponse> userProfilePhotos,
        UserAgreementResponse userAgreements
) {

    public static UserDetailResponse of(
            final User user,
            final List<IdealTypeMapper> idealTypeMappers,
            final List<InterestMapper> interestMappers,
            final List<UserProfilePhoto> profilePhotoMappers,
            final UserLocationInfo userLocationInfo,
            final UserAgreement userAgreement) {

        return new UserDetailResponse(
                user.getUsername(),
                user.getUserUuid(),
                ConvertAgeUtils.covertBeforeBirthAge(user.getBirthDay()),
                user.getIntroduction(),
                userLocationInfo.getAddress(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getBirthDay(),
                user.getGender(),
                user.getPreferGender(),
                user.getTall(),
                user.getSmoking(),
                user.getDrinking(),
                user.getReligion(),
                idealTypeMappers.stream().map(IdealTypeResponse::of).toList(),
                interestMappers.stream().map(InterestResponse::of).toList(),
                profilePhotoMappers.stream().map(UserProfilePhotoResponse::of).toList(),
                UserAgreementResponse.of(userAgreement)
        );
    }
}
