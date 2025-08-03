package com.tht.domain.entity.user;

import com.tht.enums.agreement.AgreementCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@ToString
@Table
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
    private Instant createdAt;

    @LastModifiedDate
    @Column
    private Instant lastModifiedAt;

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

    public boolean getAgreementStatus(final AgreementCategory category) {
        return switch (category) {
            case SERVICE_USE_AGREE -> serviceUseAgree;
            case PERSONAL_PRIVACY_INFO_AGREE -> personalPrivacyInfoAgree;
            case LOCATION_SERVICE_AGREE -> locationServiceAgree;
            case MARKETING_AGREE -> marketingAgree;
        };
    }
}
