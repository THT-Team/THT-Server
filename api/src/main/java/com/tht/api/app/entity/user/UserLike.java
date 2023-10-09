package com.tht.api.app.entity.user;

import com.tht.api.app.entity.Auditable;
import com.tht.api.exception.custom.LikeException;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@Getter
@NoArgsConstructor
public class UserLike extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String userUuid;

    private String favoriteUserUuid;

    private Long dailyFallingIdx;

    @Enumerated(EnumType.STRING)
    private LikeState likeState = LikeState.WAIT;

    @Builder(access = AccessLevel.PRIVATE)
    private UserLike(Long idx, String userUuid, String favoriteUserUuid, Long dailyFallingIdx, LikeState likeState) {

        this.idx = idx;
        this.userUuid = userUuid;
        this.favoriteUserUuid = favoriteUserUuid;
        this.dailyFallingIdx = dailyFallingIdx;
        this.likeState = likeState;
    }

    public static UserLike create(final String userUuid, final String favoriteUserUuid,
        final long dailyFallingIdx) {

        return UserLike.builder()
            .userUuid(userUuid)
            .favoriteUserUuid(favoriteUserUuid)
            .dailyFallingIdx(dailyFallingIdx)
            .likeState(LikeState.WAIT)
            .build();
    }

    public static UserLike disLike(final String userUuid, final String dontFavoriteUserUuid,
        final long dailyTopicIdx) {

        return UserLike.builder()
            .userUuid(userUuid)
            .favoriteUserUuid(dontFavoriteUserUuid)
            .dailyFallingIdx(dailyTopicIdx)
            .likeState(LikeState.DISLIKE)
            .build();
    }

    public void matchSuccess() {
        this.likeState = LikeState.MATCH;
    }

    public void rejectedLike(final String userUuid) {

        validationMatchReceiver(userUuid);
        validationIsWait();

        this.likeState = LikeState.REJECT;
    }

    private void validationMatchReceiver(final String userUuid) {
        if (!this.favoriteUserUuid.equals(userUuid)) {
            throw LikeException.didNotMatchReceiver(userUuid, getIdx());
        }
    }

    private void validationIsWait() {
        if (!isLikeStateWait()) {
            throw LikeException.didNotWaitState();
        }
    }

    private boolean isLikeStateWait() {
        return this.likeState.equals(LikeState.WAIT);
    }

}
