package com.tht.api.app.facade.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tht.api.app.entity.enums.Gender;
import com.tht.api.app.entity.enums.UserFrequency;
import com.tht.api.app.entity.enums.UserReligion;
import com.tht.api.app.entity.user.User;
import com.tht.api.app.entity.user.UserLocationInfo;
import com.tht.api.app.entity.user.UserProfilePhoto;
import com.tht.api.app.facade.idealtype.response.IdealTypeResponse;
import com.tht.api.app.facade.interest.response.InterestResponse;
import com.tht.api.app.repository.mapper.IdealTypeMapper;
import com.tht.api.app.repository.mapper.InterestMapper;
import com.tht.api.app.utils.ConvertAgeUtils;

import java.util.List;

public record UserDetailResponse (
    String username,
    String userUuid,
    int age,
    String introduction,
    String address,
    String phoneNumber,
    String email,
    Gender gender,
    @JsonProperty("prefer_gender")
    Gender preferGender,
    int tall,
    UserFrequency smoking,
    UserFrequency drinking,
    UserReligion religion,
    List<IdealTypeResponse> idealTypeList,
    List<InterestResponse> interestsList,
    List<UserProfilePhotoResponse> userProfilePhotos
){

    public static UserDetailResponse of(
        final User user,
        final List<IdealTypeMapper> idealTypeMappers,
        final List<InterestMapper> interestMappers,
        final List<UserProfilePhoto> profilePhotoMappers,
        final UserLocationInfo userLocationInfo) {

        return new UserDetailResponse(
            user.getUsername(),
            user.getUserUuid(),
            ConvertAgeUtils.covertBeforeBirthAge(user.getBirthDay()),
            user.getIntroduction(),
            userLocationInfo.getAddress(),
            user.getPhoneNumber(),
            user.getEmail(),
            user.getGender(),
            user.getPreferGender(),
            user.getTall(),
            user.getSmoking(),
            user.getDrinking(),
            user.getReligion(),
            idealTypeMappers.stream().map(IdealTypeResponse::of).toList(),
            interestMappers.stream().map(InterestResponse::of).toList(),
            profilePhotoMappers.stream().map(UserProfilePhotoResponse::of).toList()
        );
    }
}
