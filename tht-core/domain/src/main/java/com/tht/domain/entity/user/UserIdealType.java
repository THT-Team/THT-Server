package com.tht.domain.entity.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@Getter
@Table
@NoArgsConstructor
public class UserIdealType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long idx;

    @Column
    private String userUuid;

    @Column
    private Integer idealTypeIdx;

    @Builder(access = AccessLevel.PRIVATE)
    public UserIdealType(final Long idx, final String userUuid, final Integer idealTypeIdx) {
        this.idx = idx;
        this.userUuid = userUuid;
        this.idealTypeIdx = idealTypeIdx;
    }

    public static UserIdealType create(final String userUuid, final Integer idealTypeIdx) {
        return UserIdealType.builder()
            .userUuid(userUuid)
            .idealTypeIdx(idealTypeIdx)
            .build();
    }
}
