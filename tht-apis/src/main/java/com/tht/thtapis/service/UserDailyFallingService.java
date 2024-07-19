package com.tht.thtapis.service;

import java.util.List;
import java.util.Optional;

import com.tht.infra.dailyfalling.UserDailyFalling;
import com.tht.infra.dailyfalling.UserDailyFallingException;
import com.tht.infra.dailyfalling.mapper.DailyFallingTimeMapper;
import com.tht.infra.dailyfalling.mapper.UserDailyFallingMapper;
import com.tht.enums.user.Gender;
import com.tht.infra.user.mapper.MainScreenUserInfoMapper;
import com.tht.infra.user.repository.UserDailyFallingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDailyFallingService {

    private final UserDailyFallingRepository userDailyFallingRepository;

    public void choice(final long dailyFallingIdx, final String userUuid) {

        final Optional<UserDailyFallingMapper> choosing = findByUserUuid(userUuid);

        if (choosing.isPresent()) {
            throw UserDailyFallingException.alreadyChoice();
        }

        userDailyFallingRepository.save(UserDailyFalling.of(dailyFallingIdx, userUuid));
    }

    private Optional<UserDailyFallingMapper> findByUserUuid(String userUuid) {
        return userDailyFallingRepository.findByUserChoosingToDayFalling(userUuid);
    }

    public Optional<DailyFallingTimeMapper> findChooseTodayDailyFallingInfo(final String userUuid) {

        return userDailyFallingRepository.findFallingTimeInfo(userUuid);
    }

    public List<MainScreenUserInfoMapper> findAllMatchingFallingUser(final long dailyFallingIdx,
                                                                     final Long userDailyFallingCourserIdx,
                                                                     final String myUuid, final Gender myGender, final Gender myPreferGender,
                                                                     final Integer size) {

        return userDailyFallingRepository.findAllMatchingFallingUser(dailyFallingIdx,
            userDailyFallingCourserIdx, myUuid, myGender, myPreferGender, size);
    }
}
