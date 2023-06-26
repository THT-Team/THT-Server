package com.tht.api.app.entity.user;

import com.tht.api.app.entity.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user_daily_falling")
public class UserDailyFalling extends Auditable {

    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "user_uuid")
    private String userUuid;

    @Column(name = "daily_falling_idx")
    private Long dailyFallingIdx;

    @Builder
    private UserDailyFalling(final Long idx, final String userUuid, final Long dailyFallingIdx) {
        this.idx = idx;
        this.userUuid = userUuid;
        this.dailyFallingIdx = dailyFallingIdx;
    }

    public static UserDailyFalling of(final long dailyFallingIdx, final String userUuid) {
        return UserDailyFalling.builder()
            .userUuid(userUuid)
            .dailyFallingIdx(dailyFallingIdx)
            .build();
    }

}
