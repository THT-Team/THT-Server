package com.tht.infra.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@Getter
@Table
@NoArgsConstructor
public class UserInterests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long idx;

    @Column
    private String userUuid;

    @Column
    private Integer interestIdx;

    @Builder(access = AccessLevel.PRIVATE)
    public UserInterests(final Long idx, final String userUuid, final Integer interestIdx) {
        this.idx = idx;
        this.userUuid = userUuid;
        this.interestIdx = interestIdx;
    }

    public static UserInterests create(final String userUuid, final Integer interestIdx) {
        return UserInterests.builder()
            .userUuid(userUuid)
            .interestIdx(interestIdx)
            .build();
    }
}
