package com.tht.api.app.repository.mapper;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDate;
import java.util.Set;

public record MainScreenUserInfoMapper(
    String username,
    String userUuid,
    LocalDate birthDay,
    String address,
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
