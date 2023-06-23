package com.tht.api.app.fixture.user;

import com.tht.api.app.fixture.meta.IdealTypeFixture;
import com.tht.api.app.fixture.meta.InterestFixture;
import com.tht.api.app.repository.mapper.IdealTypeMapper;
import com.tht.api.app.repository.mapper.InterestMapper;
import com.tht.api.app.repository.mapper.MainScreenUserInfoMapper;
import com.tht.api.app.repository.mapper.UserProfilePhotoMapper;
import java.time.LocalDate;

public class MainScreenUserInfoMapperFixture {

    private static final String username = "매칭된 유저 이름";
    private static final String userUuid = "uuid-uuid";
    private static final LocalDate birthDay = LocalDate.of(1997, 11, 4);
    private static final String address = "인천광역시 부평구";
    private static final IdealTypeMapper idealTypeMapper = IdealTypeFixture.mapperMake();
    private static final InterestMapper interestMapper = InterestFixture.mapperMake();
    private static final UserProfilePhotoMapper userProfilePhotoMapper = UserProfilePhotoFixture.mapperMake();
    private static final String introduction = "유저 자기소개";
    private static final long userDailyFallingIdx = 1;

    public static MainScreenUserInfoMapper make() {
        return new MainScreenUserInfoMapper(
            username,
            userUuid,
            birthDay,
            address,
            idealTypeMapper,
            interestMapper,
            userProfilePhotoMapper,
            introduction,
            userDailyFallingIdx
        );
    }

    public static MainScreenUserInfoMapper make(final IdealTypeMapper idealTypeMapper) {
        return new MainScreenUserInfoMapper(
            username,
            userUuid,
            birthDay,
            address,
            idealTypeMapper,
            interestMapper,
            userProfilePhotoMapper,
            introduction,
            userDailyFallingIdx
        );
    }

    public static MainScreenUserInfoMapper make(final InterestMapper interestMapper) {
        return new MainScreenUserInfoMapper(
            username,
            userUuid,
            birthDay,
            address,
            idealTypeMapper,
            interestMapper,
            userProfilePhotoMapper,
            introduction,
            userDailyFallingIdx
        );
    }

    public static MainScreenUserInfoMapper make(final UserProfilePhotoMapper userProfilePhotoMapper) {
        return new MainScreenUserInfoMapper(
            username,
            userUuid,
            birthDay,
            address,
            idealTypeMapper,
            interestMapper,
            userProfilePhotoMapper,
            introduction,
            userDailyFallingIdx
        );
    }
}
