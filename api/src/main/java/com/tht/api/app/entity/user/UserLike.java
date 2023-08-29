package com.tht.api.app.entity.user;

import com.tht.api.app.entity.Auditable;
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
    private LikeState likeState;

    @Builder(access = AccessLevel.PRIVATE)
    private UserLike(Long idx, String userUuid, String favoriteUserUuid, Long dailyFallingIdx,
        LikeState likeState) {

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

    public void matchSuccess() {
        this.likeState = LikeState.MATCH;
    }

    public void rejectedLike() {
        this.likeState = LikeState.REJECT;
    }
}
