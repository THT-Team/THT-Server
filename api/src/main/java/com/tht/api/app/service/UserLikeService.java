package com.tht.api.app.service;

import com.tht.api.app.entity.user.UserLike;
import com.tht.api.app.repository.user.UserLikeRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserLikeService {

    private final UserLikeRepository userLikeRepository;

    @Transactional
    public UserLike save(final String myUuid, final String favoriteUserUuid, final long dailyTopicIdx) {
        return userLikeRepository.save(UserLike.create(myUuid, favoriteUserUuid, dailyTopicIdx));
    }

    public Optional<UserLike> findIsMatchedLike(final String myUuid, final String favoriteUserUuid,
        final long dailyTopicIdx) {

        return userLikeRepository.findByUserUuidAndAndFavoriteUserUuidAndDailyFallingIdx(
            favoriteUserUuid, myUuid, dailyTopicIdx);
    }
}
