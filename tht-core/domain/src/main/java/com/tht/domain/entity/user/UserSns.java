package com.tht.domain.entity.user;

import com.tht.domain.entity.Auditable;
import com.tht.enums.user.SNSType;
import com.tht.enums.user.converter.SNSTypeConverter;
import com.tht.thtcommonutils.utils.LogWriteUtils;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
@Table
public class UserSns extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long idx;

    @Column
    private String userUuid;

    @Convert(converter = SNSTypeConverter.class)
    @Column
    private SNSType snsType;

    @Column
    private String snsUniqueId;

    @Column
    private String email;

    @Builder(access = AccessLevel.PRIVATE)
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
