package com.tht.domain.entity.user.service;

import com.tht.domain.entity.user.UserAgreement;
import com.tht.domain.entity.user.UserProfilePhoto;
import com.tht.domain.entity.user.repository.querydsl.mapper.UserDetailMapper;
import com.tht.enums.user.Gender;
import com.tht.enums.user.SNSType;
import com.tht.enums.user.UserFrequency;
import com.tht.enums.user.UserReligion;
import com.tht.thtcommonutils.utils.CustomDateFormatUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record UserDetailDto(
    String phoneNumber,
    String username,
    String birthDay,
    String email,
    Set<SNSType> snsSignUpList,
    UserAgreement userAgreement,
    Gender gender,
    Gender preferGender,
    Set<UserProfilePhoto> profileUrl,
    int tall,
    UserFrequency drinkStatus,
    UserReligion religion,
    UserFrequency smokingStatus,
    String userLocation,
    Set<String> interests,
    Set<String> idealTypes

) {
    public static UserDetailDto ofMapper(final List<UserDetailMapper> mappers) {

        final int first = 0;
        final UserDetailMapper simpleInfo = mappers.get(first);

        return new UserDetailDto(
            simpleInfo.phoneNumber(),
            simpleInfo.username(),
            convertLocalDateToString(simpleInfo.birthDay()),
            simpleInfo.email(),
            mappers.stream().map(UserDetailMapper::snsSignUp).collect(Collectors.toSet()),
            simpleInfo.userAgreement(),
            simpleInfo.gender(),
            simpleInfo.preferGender(),
            mappers.stream().map(UserDetailMapper::profiles).collect(Collectors.toSet()),
            simpleInfo.tall(),
            simpleInfo.drinkStatus(),
            simpleInfo.religion(),
            simpleInfo.smokingStatus(),
            simpleInfo.userLocation(),
            mappers.stream().map(UserDetailMapper::interests).collect(Collectors.toSet()),
            mappers.stream().map(UserDetailMapper::idealTypes).collect(Collectors.toSet())
        );
    }

    private static String convertLocalDateToString(final LocalDate date) {
        return date.format(CustomDateFormatUtils.getDotDateInstance());
    }
}
