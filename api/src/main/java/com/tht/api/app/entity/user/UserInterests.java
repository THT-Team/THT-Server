package com.tht.api.app.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "user_interests")
@NoArgsConstructor
public class UserInterests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long idx;

    @Column(name = "user_uuid")
    private String userUuid;

    @Column(name = "interest_idx")
    private Long interestIdx;

    @Builder(access = AccessLevel.PRIVATE)
    public UserInterests(final Long idx, final String userUuid, final Long interestIdx) {
        this.idx = idx;
        this.userUuid = userUuid;
        this.interestIdx = interestIdx;
    }

    public static UserInterests create(final String userUuid, final Long interestIdx) {
        return UserInterests.builder()
            .userUuid(userUuid)
            .interestIdx(interestIdx)
            .build();
    }
}
