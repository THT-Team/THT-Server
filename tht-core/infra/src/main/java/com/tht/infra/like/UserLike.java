package com.tht.infra.like;

import com.tht.enums.user.LikeState;
import com.tht.infra.Auditable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@Getter
@NoArgsConstructor
public class UserLike extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String userUuid;

    private String targetUserUuid;

    private Long dailyFallingIdx;

    @Enumerated(EnumType.STRING)
    private LikeState likeState = LikeState.LIKE;

    @Builder(access = AccessLevel.PRIVATE)
    private UserLike(Long idx, String userUuid, String targetUserUuid, Long dailyFallingIdx, LikeState likeState) {

        this.idx = idx;
        this.userUuid = userUuid;
        this.targetUserUuid = targetUserUuid;
        this.dailyFallingIdx = dailyFallingIdx;
        this.likeState = likeState;
    }

    public static UserLike create(final String userUuid, final String favoriteUserUuid,
        final long dailyFallingIdx) {

        return UserLike.builder()
            .userUuid(userUuid)
            .targetUserUuid(favoriteUserUuid)
            .dailyFallingIdx(dailyFallingIdx)
            .likeState(LikeState.LIKE)
            .build();
    }

    public static UserLike disLike(final String userUuid, final String dontFavoriteUserUuid,
        final long dailyTopicIdx) {

        return UserLike.builder()
            .userUuid(userUuid)
            .targetUserUuid(dontFavoriteUserUuid)
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
        if (!this.targetUserUuid.equals(userUuid)) {
            throw LikeException.didNotMatchReceiver(userUuid, getIdx());
        }
    }

    private void validationIsWait() {
        if (!isLikeStateWait()) {
            throw LikeException.didNotWaitState();
        }
    }

    private boolean isLikeStateWait() {
        return this.likeState.equals(LikeState.LIKE);
    }

}
