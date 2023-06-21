package com.tht.api.app.service;

import com.tht.api.app.entity.user.UserDailyFalling;
import com.tht.api.app.repository.user.UserDailyFallingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDailyFallingService {

    private final UserDailyFallingRepository userDailyFallingRepository;
    public void insert(final long dailyFallingIdx, final String userUuid) {

        userDailyFallingRepository.save(UserDailyFalling.of(dailyFallingIdx, userUuid));
    }
}
