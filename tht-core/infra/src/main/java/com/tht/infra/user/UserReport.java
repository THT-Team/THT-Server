package com.tht.infra.user;

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
public class UserReport {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String userUuid;

    @Column
    private String reportUserUuid;

    @Column
    private String reason;

    @CreatedDate
    @Column
    private LocalDateTime createdAt;

    @Builder(access = AccessLevel.PRIVATE)
    private UserReport(final Long idx, final String userUuid, final String reportUserUuid,
        final String reason, final LocalDateTime createdAt) {

        this.idx = idx;
        this.userUuid = userUuid;
        this.reportUserUuid = reportUserUuid;
        this.reason = reason;
        this.createdAt = createdAt;
    }

    public static UserReport create(final String userUuid, final String reportUserUuid,
        final String reason) {

        return UserReport.builder()
            .userUuid(userUuid)
            .reportUserUuid(reportUserUuid)
            .reason(reason)
            .build();
    }
}
