package com.tht.domain.entity.user.repository.querydsl;

import com.tht.domain.entity.dailyfalling.mapper.DailyFallingTimeMapper;
import com.tht.domain.entity.dailyfalling.mapper.UserDailyFallingMapper;
import com.tht.enums.user.Gender;
import com.tht.domain.entity.user.mapper.MainScreenUserInfoMapper;

import java.util.List;
import java.util.Optional;

public interface UserDailyFallingCustomRepository {

    Optional<UserDailyFallingMapper> findByUserChoosingToDayFalling(final String userUuid);

    Optional<DailyFallingTimeMapper> findFallingTimeInfo(final String userUuid);

    List<MainScreenUserInfoMapper> findAllMatchingFallingUser(
        final Long dailyFallingIdx, final Long userDailyFallingCourserIdx, final String userUuid,
        final Gender myGender, final Gender myPreferGender, final Integer size);

    long countByDailyFallingId(Long dailyFallingIdx);
}
