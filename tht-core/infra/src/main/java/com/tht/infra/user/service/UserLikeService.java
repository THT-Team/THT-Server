package com.tht.infra.user.service;

import java.util.List;
import java.util.Optional;

import com.tht.infra.like.LikeException;
import com.tht.infra.like.LikeReceiveMapper;
import com.tht.infra.like.UserLike;
import com.tht.infra.like.UserLikeRepository;
import com.tht.enums.user.LikeState;
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

    @Transactional
    public void disLike(final String myUuid, final String dontFavoriteUserUuid,
        final long dailyTopicIdx) {
        userLikeRepository.save(UserLike.disLike(myUuid, dontFavoriteUserUuid, dailyTopicIdx));
    }

    public Optional<UserLike> findIsMatchedLike(final String myUuid, final String favoriteUserUuid,
        final long dailyTopicIdx) {

        return userLikeRepository.findByUserUuidAndTargetUserUuidAndDailyFallingIdxAndLikeState(
            favoriteUserUuid, myUuid, dailyTopicIdx, LikeState.LIKE);
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
