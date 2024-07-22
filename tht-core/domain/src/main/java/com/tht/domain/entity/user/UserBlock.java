package com.tht.domain.entity.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@ToString
@Table
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class UserBlock {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String userUuid;

    @Column
    private String blockUserUuid;

    @CreatedDate
    @Column
    private LocalDateTime createdAt;

    @Builder(access = AccessLevel.PRIVATE)
    private UserBlock(final Long idx, final String userUuid, final String blockUserUuid,
        final LocalDateTime createdAt) {

        this.idx = idx;
        this.userUuid = userUuid;
        this.blockUserUuid = blockUserUuid;
        this.createdAt = createdAt;
    }

    public static UserBlock create(final String userUuid, final String blockUserUuid) {
        return UserBlock.builder()
            .userUuid(userUuid)
            .blockUserUuid(blockUserUuid)
            .build();
    }
}
