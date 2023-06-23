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
@Table(name = "user_ideal_type")
@NoArgsConstructor
public class UserIdealType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long idx;

    @Column(name = "user_uuid")
    private String userUuid;

    @Column(name = "ideal_type_idx")
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
