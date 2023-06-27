package com.tht.api.app.repository.user.querydsl;

import com.tht.api.app.repository.mapper.DailyFallingTimeMapper;
import com.tht.api.app.repository.mapper.MainScreenUserInfoMapper;
import com.tht.api.app.repository.mapper.UserDailyFallingMapper;
import java.util.List;
import java.util.Optional;

public interface UserDailyFallingCustomRepository {

    Optional<UserDailyFallingMapper> findByUserChoosingToDayFalling(final String userUuid);

    List<MainScreenUserInfoMapper> findAllMatchingFallingUser(final long dailyFallingIdx,
        final List<String> alreadySeenUserUuidList, final Long userDailyFallingCourserIdx,
        final String myUuid, final Integer size);

    Optional<DailyFallingTimeMapper> findFallingTimeInfo(final String userUuid);
}
