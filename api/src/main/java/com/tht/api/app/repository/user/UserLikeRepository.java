package com.tht.api.app.repository.user;

import com.tht.api.app.entity.user.UserLike;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLikeRepository extends JpaRepository<UserLike, Long> {

    Optional<UserLike> findByUserUuidAndAndFavoriteUserUuidAndDailyFallingIdx(
        String userUuid, String favoriteUserUuid, long dailyFallingIdx);
}
