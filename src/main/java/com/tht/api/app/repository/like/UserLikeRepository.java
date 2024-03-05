package com.tht.api.app.repository.like;

import com.tht.api.app.entity.user.LikeState;
import com.tht.api.app.entity.user.UserLike;
import com.tht.api.app.repository.like.querydsl.UserLikeCustomRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLikeRepository extends JpaRepository<UserLike, Long>,
    UserLikeCustomRepository {

    Optional<UserLike> findByUserUuidAndTargetUserUuidAndDailyFallingIdxAndLikeState(
        String userUuid, String targetUserUuid, long dailyFallingIdx, LikeState likeState);
}
