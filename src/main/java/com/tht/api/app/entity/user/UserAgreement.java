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
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@ToString
@Table(name = "user_agreement")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@DynamicUpdate
public class UserAgreement {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String userUuid;

    @Column
    private boolean serviceUseAgree;

    @Column
    private boolean personalPrivacyInfoAgree;

    @Column
    private boolean locationServiceAgree;

    @Column
    private boolean marketingAgree;

    @CreatedDate
    @Column
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column
    private LocalDateTime lastModifiedAt;

    @Builder(access = AccessLevel.PRIVATE)
    public UserAgreement(final Long idx, final String userUuid, final boolean serviceUseAgree,
        final boolean personalPrivacyInfoAgree, final boolean locationServiceAgree,
        final boolean marketingAgree) {

        this.idx = idx;
        this.userUuid = userUuid;
        this.serviceUseAgree = serviceUseAgree;
        this.personalPrivacyInfoAgree = personalPrivacyInfoAgree;
        this.locationServiceAgree = locationServiceAgree;
        this.marketingAgree = marketingAgree;
    }

    public static UserAgreement create(final String userUuid, final boolean serviceUseAgree,
        final boolean personalPrivacyInfoAgree, final boolean locationServiceAgree,
        final boolean marketingAgree) {

        return UserAgreement.builder()
            .userUuid(userUuid)
            .serviceUseAgree(serviceUseAgree)
            .personalPrivacyInfoAgree(personalPrivacyInfoAgree)
            .locationServiceAgree(locationServiceAgree)
            .marketingAgree(marketingAgree)
            .build();
    }

    public void modifyMarketingAgree(final boolean marketingAlarm) {
        this.marketingAgree = marketingAlarm;
    }
}
