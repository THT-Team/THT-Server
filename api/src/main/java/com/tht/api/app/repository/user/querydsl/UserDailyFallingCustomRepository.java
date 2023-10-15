package com.tht.api.app.repository.user.querydsl;

import com.tht.api.app.entity.enums.Gender;
import com.tht.api.app.repository.mapper.DailyFallingTimeMapper;
import com.tht.api.app.repository.mapper.MainScreenUserInfoMapper;
import com.tht.api.app.repository.mapper.UserDailyFallingMapper;
import java.util.List;
import java.util.Optional;

public interface UserDailyFallingCustomRepository {

    Optional<UserDailyFallingMapper> findByUserChoosingToDayFalling(final String userUuid);

    Optional<DailyFallingTimeMapper> findFallingTimeInfo(final String userUuid);

    List<MainScreenUserInfoMapper> findAllMatchingFallingUser(
        final Long dailyFallingIdx, final Long userDailyFallingCourserIdx, final String userUuid,
        final Gender myGender, final Gender myPreferGender, final Integer size);

}
