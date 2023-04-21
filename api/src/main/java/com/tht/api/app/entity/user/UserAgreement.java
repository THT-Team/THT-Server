package com.tht.api.app.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "user_agreement")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class UserAgreement {

    @Id
    private Long idx;

    @Column(name = "user_uuid")
    private String userUuid;

    @Column(name = "service_use_agree")
    private boolean serviceUseAgree;

    @Column(name = "personal_privacy_info_agree")
    private boolean personalPrivacyInfoAgree;

    @Column(name = "location_service_agree")
    private boolean locationServiceAgree;

    @Column(name = "marketing_agree")
    private boolean marketingAgree;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "last_modified_at")
    private LocalDateTime lastModifiedAt;

    @Builder(access = AccessLevel.PRIVATE)
    public UserAgreement(final Long idx, final boolean serviceUseAgree,
        final boolean personalPrivacyInfoAgree, final boolean locationServiceAgree,
        final boolean marketingAgree) {

        this.idx = idx;
        this.serviceUseAgree = serviceUseAgree;
        this.personalPrivacyInfoAgree = personalPrivacyInfoAgree;
        this.locationServiceAgree = locationServiceAgree;
        this.marketingAgree = marketingAgree;
    }

    public static UserAgreement create(final boolean serviceUseAgree,
        final boolean personalPrivacyInfoAgree, final boolean locationServiceAgree,
        final boolean marketingAgree) {

        return UserAgreement.builder()
            .serviceUseAgree(serviceUseAgree)
            .personalPrivacyInfoAgree(personalPrivacyInfoAgree)
            .locationServiceAgree(locationServiceAgree)
            .marketingAgree(marketingAgree)
            .build();
    }

    public void setUserUuid(final String userUuid) {
        if (Objects.nonNull(this.userUuid)) {
            throw new IllegalArgumentException(idx + " : 유저 약관동의테이블에 이미 user uuid 가 존재합니다.");
        }
        this.userUuid = userUuid;
    }

}
