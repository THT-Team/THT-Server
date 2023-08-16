package com.tht.api.app.entity.user;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class UserWithDrawLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String reason;

    private String feedBack;

    @CreatedDate
    private LocalDateTime createdAt;

    private UserWithDrawLog(final String reason, final String feedBack) {
        this.reason = reason;
        this.feedBack = feedBack;
    }

    public static UserWithDrawLog of(final String reason, final String feedBack) {
        return new UserWithDrawLog(reason, feedBack);
    }
}
