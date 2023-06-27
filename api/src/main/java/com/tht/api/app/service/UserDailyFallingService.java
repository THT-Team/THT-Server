package com.tht.api.app.service;

import com.tht.api.app.entity.user.UserDailyFalling;
import com.tht.api.app.repository.mapper.MainScreenUserInfoMapper;
import com.tht.api.app.repository.mapper.UserDailyFallingMapper;
import com.tht.api.app.repository.user.UserDailyFallingRepository;
import com.tht.api.exception.custom.UserDailyFallingException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDailyFallingService {

    private final UserDailyFallingRepository userDailyFallingRepository;

    public void choice(final long dailyFallingIdx, final String userUuid) {

        final Optional<UserDailyFallingMapper> choosing = userDailyFallingRepository.findByUserChoosingToDayFalling(
            userUuid);

        if (choosing.isPresent()) {
            throw UserDailyFallingException.alreadyChoice();
        }

        userDailyFallingRepository.save(UserDailyFalling.of(dailyFallingIdx, userUuid));
    }

    public long findToDayFalling(final String userUuid) {

        return userDailyFallingRepository.findByUserChoosingToDayFalling(userUuid)
            .orElseThrow(UserDailyFallingException::notChoice)
            .dailyFallingIdx();
    }

    public List<MainScreenUserInfoMapper> findAllMatchingFallingUser(final long dailyFallingIdx,
        final List<String> alreadySeenUserUuidList, final Long userDailyFallingCourserIdx,
        final String myUuid, final Integer size) {
        return userDailyFallingRepository.findAllMatchingFallingUser(dailyFallingIdx,
            alreadySeenUserUuidList, userDailyFallingCourserIdx, myUuid, size);
    }
}
