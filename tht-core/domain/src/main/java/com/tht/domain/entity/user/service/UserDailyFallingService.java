package com.tht.domain.entity.user.service;

import com.tht.domain.entity.dailyfalling.UserDailyFalling;
import com.tht.domain.entity.dailyfalling.UserDailyFallingException;
import com.tht.domain.entity.dailyfalling.mapper.DailyFallingTimeMapper;
import com.tht.domain.entity.dailyfalling.mapper.UserDailyFallingMapper;
import com.tht.domain.entity.user.mapper.MainScreenUserInfoMapper;
import com.tht.domain.entity.user.repository.UserDailyFallingRepository;
import com.tht.enums.user.Gender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public long getCountFallingUsers(final Long dailyFallingIdx) {
        return userDailyFallingRepository.countByDailyFallingId(dailyFallingIdx);
    }
}
