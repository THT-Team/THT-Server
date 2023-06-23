package com.tht.api.app.repository.mapper;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDate;

public record MainScreenUserInfoMapper(
    String username,
    String userUuid,
    LocalDate birthDay,
    String address,
    IdealTypeMapper idealTypeMapper,
    InterestMapper interestMapper,
    UserProfilePhotoMapper userProfilePhotoMapper,
    String introduction,
    long userDailyFallingIdx
) {

    @QueryProjection
    public MainScreenUserInfoMapper{}
}
