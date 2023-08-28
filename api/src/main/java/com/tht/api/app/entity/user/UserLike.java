package com.tht.api.app.entity.user;

import com.tht.api.app.entity.Auditable;
import jakarta.persistence.Entity;
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

    @Builder(access = AccessLevel.PRIVATE)
    public UserLike(Long idx, String userUuid, String favoriteUserUuid, Long dailyFallingIdx) {
        this.idx = idx;
        this.userUuid = userUuid;
        this.favoriteUserUuid = favoriteUserUuid;
        this.dailyFallingIdx = dailyFallingIdx;
    }

    public static UserLike create(final String userUuid, final String favoriteUserUuid,
        final long dailyFallingIdx) {

        return UserLike.builder()
            .userUuid(userUuid)
            .favoriteUserUuid(favoriteUserUuid)
            .dailyFallingIdx(dailyFallingIdx)
            .build();
    }
}
