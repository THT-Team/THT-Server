package com.tht.infra.user.repository.querydsl;

import com.tht.infra.dailyfalling.mapper.DailyFallingTimeMapper;
import com.tht.infra.dailyfalling.mapper.UserDailyFallingMapper;
import com.tht.infra.user.enums.Gender;
import com.tht.infra.user.mapper.MainScreenUserInfoMapper;

import java.util.List;
import java.util.Optional;

public interface UserDailyFallingCustomRepository {

    Optional<UserDailyFallingMapper> findByUserChoosingToDayFalling(final String userUuid);

    Optional<DailyFallingTimeMapper> findFallingTimeInfo(final String userUuid);

    List<MainScreenUserInfoMapper> findAllMatchingFallingUser(
        final Long dailyFallingIdx, final Long userDailyFallingCourserIdx, final String userUuid,
        final Gender myGender, final Gender myPreferGender, final Integer size);

}
