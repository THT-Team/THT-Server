package com.tht.domain.entity.like;

import com.tht.enums.user.LikeState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserLikeRepository extends JpaRepository<UserLike, Long>,
    UserLikeCustomRepository {

    Optional<UserLike> findByUserUuidAndTargetUserUuidAndDailyFallingIdxAndLikeState(
        String userUuid, String targetUserUuid, long dailyFallingIdx, LikeState likeState);

    @Transactional
    void deleteAllByUserUuid(String userUuid);
}
