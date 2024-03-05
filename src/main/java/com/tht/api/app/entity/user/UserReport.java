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
@Table(name = "user_report")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class UserReport {

    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "user_uuid")
    private String userUuid;

    @Column(name = "report_user_uuid")
    private String reportUserUuid;

    @Column
    private String reason;

    @CreatedDate
    @Column(name = "created_at")
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
