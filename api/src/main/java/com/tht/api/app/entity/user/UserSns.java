package com.tht.api.app.entity.user;

import com.tht.api.app.config.utils.LogWriteUtils;
import com.tht.api.app.entity.Auditable;
import com.tht.api.app.entity.enums.SNSType;
import com.tht.api.app.entity.enums.converter.SNSTypeConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
@Table(name = "user_sns")
public class UserSns extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long idx;

    @Column(name = "user_uuid")
    private String userUuid;

    @Convert(converter = SNSTypeConverter.class)
    @Column(name = "sns_type")
    private SNSType snsType;

    @Column(name = "sns_unique_id")
    private String snsUniqueId;

    @Column(name = "email")
    private String email;

    @Builder
    private UserSns(final String userUuid, final SNSType snsType, final String snsUniqueId,
        final String email) {

        this.userUuid = userUuid;
        this.snsType = snsType;
        this.snsUniqueId = snsUniqueId;
        this.email = email;
    }

    public static UserSns create(final String userUuid, final SNSType snsType,
        final String snsUniqueId, final String email) {

        final UserSns userSns = UserSns.builder()
            .userUuid(userUuid)
            .snsType(snsType)
            .snsUniqueId(snsUniqueId)
            .email(email)
            .build();

        LogWriteUtils.createInfo(userSns);
        return userSns;
    }
}
