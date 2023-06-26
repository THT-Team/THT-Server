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
import lombok.ToString;

@Entity
@ToString
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
