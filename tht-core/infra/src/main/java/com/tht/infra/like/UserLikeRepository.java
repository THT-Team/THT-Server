package com.tht.infra.like;

import com.tht.infra.user.enums.LikeState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLikeRepository extends JpaRepository<UserLike, Long>,
    UserLikeCustomRepository {

    Optional<UserLike> findByUserUuidAndTargetUserUuidAndDailyFallingIdxAndLikeState(
        String userUuid, String targetUserUuid, long dailyFallingIdx, LikeState likeState);
}
