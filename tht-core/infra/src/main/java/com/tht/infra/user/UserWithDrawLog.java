package com.tht.infra.user;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class UserWithDrawLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String userUuid;

    private String reason;

    private String feedBack;

    @CreatedDate
    private LocalDateTime createdAt;

    private UserWithDrawLog(final String userUuid, final String reason, final String feedBack) {
        this.userUuid = userUuid;
        this.reason = reason;
        this.feedBack = feedBack;
    }

    public static UserWithDrawLog of(final String userUuid, final String reason, final String feedBack) {
        return new UserWithDrawLog(userUuid, reason, feedBack);
    }
}
