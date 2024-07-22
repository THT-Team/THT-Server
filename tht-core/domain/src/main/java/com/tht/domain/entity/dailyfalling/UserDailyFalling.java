package com.tht.domain.entity.dailyfalling;

import com.tht.domain.entity.Auditable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table
public class UserDailyFalling extends Auditable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String userUuid;

    @Column
    private Long dailyFallingIdx;

    @Builder(access = AccessLevel.PRIVATE)
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
