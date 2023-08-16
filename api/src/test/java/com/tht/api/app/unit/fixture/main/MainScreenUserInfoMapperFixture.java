package com.tht.api.app.unit.fixture.main;

import com.tht.api.app.unit.fixture.meta.IdealTypeFixture;
import com.tht.api.app.unit.fixture.meta.InterestFixture;
import com.tht.api.app.unit.fixture.user.UserProfilePhotoFixture;
import com.tht.api.app.repository.mapper.IdealTypeMapper;
import com.tht.api.app.repository.mapper.InterestMapper;
import com.tht.api.app.repository.mapper.MainScreenUserInfoMapper;
import com.tht.api.app.repository.mapper.UserProfilePhotoMapper;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainScreenUserInfoMapperFixture {

    private static final String username = "매칭된 유저 이름";
    private static final String userUuid = "uuid-uuid";
    private static final LocalDate birthDay = LocalDate.of(1997, 11, 4);
    private static final String address = "인천광역시 부평구";
    private static final Set<IdealTypeMapper> idealTypeMapper = new HashSet<>(List.of(
        IdealTypeFixture.mapperMake()));
    private static final Set<InterestMapper> interestMapper = new HashSet<>(List.of(
        InterestFixture.mapperMake()));
    private static final Set<UserProfilePhotoMapper> userProfilePhotoMapper = new HashSet<>(List.of(
        UserProfilePhotoFixture.mapperMake()));
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

}
