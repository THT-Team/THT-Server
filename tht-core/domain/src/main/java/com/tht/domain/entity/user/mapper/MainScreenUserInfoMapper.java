package com.tht.domain.entity.user.mapper;

import com.querydsl.core.annotations.QueryProjection;
import com.tht.domain.entity.idealtype.IdealTypeMapper;
import com.tht.domain.entity.interesst.InterestMapper;
import com.tht.enums.user.UserFrequency;
import com.tht.enums.user.UserReligion;

import java.time.LocalDate;
import java.util.Set;

public record MainScreenUserInfoMapper(
    String username,
    String userUuid,
    LocalDate birthDay,
    String address,
    UserFrequency smoking,
    UserFrequency drinking,
    UserReligion religion,
    Set<IdealTypeMapper> idealTypeMapper,
    Set<InterestMapper> interestMapper,
    Set<UserProfilePhotoMapper> userProfilePhotoMapper,
    String introduction,
    long userDailyFallingIdx,
    float lat,
    float lon
) {

    @QueryProjection
    public MainScreenUserInfoMapper {}
}
