package com.tht.api.app.service;

import com.tht.api.app.entity.user.UserLike;
import com.tht.api.app.repository.like.UserLikeRepository;
import com.tht.api.app.repository.mapper.LikeReceiveMapper;
import com.tht.api.exception.custom.LikeException;
import java.util.List;
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

        return userLikeRepository.findByUserUuidAndFavoriteUserUuidAndDailyFallingIdx(
            favoriteUserUuid, myUuid, dailyTopicIdx);
    }

    public List<LikeReceiveMapper> findReceivedLikes(final String userUuid, final int size,
        final Long dailyFallingIdx, final Long likeIdx) {

        return userLikeRepository.findReceivedLikes(userUuid, size, dailyFallingIdx, likeIdx);
    }

    @Transactional
    public void reject(final String userUuid, final long likeIdx) {

        final UserLike userLike = userLikeRepository.findById(likeIdx)
            .orElseThrow(
                () -> LikeException.notFound(likeIdx)
            );

        userLike.rejectedLike(userUuid);
    }
}
