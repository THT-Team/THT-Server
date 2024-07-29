package com.tht.domain.entity.user.repository.querydsl.mapper;

import com.querydsl.core.annotations.QueryProjection;
import com.tht.domain.entity.user.UserAgreement;
import com.tht.domain.entity.user.UserProfilePhoto;
import com.tht.enums.user.Gender;
import com.tht.enums.user.SNSType;
import com.tht.enums.user.UserFrequency;
import com.tht.enums.user.UserReligion;

import java.time.LocalDate;

public record UserDetailMapper(
    String phoneNumber,
    String username,
    LocalDate birthDay,
    String email,
    SNSType snsSignUp,
    UserAgreement userAgreement,
    Gender gender,
    Gender preferGender,
    UserProfilePhoto profiles,
    int tall,
    UserFrequency drinkStatus,
    UserReligion religion,
    UserFrequency smokingStatus,
    String userLocation,
    String interests,
    String idealTypes
) {

    @QueryProjection
    public UserDetailMapper{}

}
