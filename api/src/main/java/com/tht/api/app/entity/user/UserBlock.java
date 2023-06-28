package com.tht.api.app.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@ToString
@Table(name = "user_block")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class UserBlock {

    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "user_uuid")
    private String userUuid;

    @Column(name = "block_user_uuid")
    private String blockUserUuid;

    @CreatedDate
    @Column(name = "created_at")
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
