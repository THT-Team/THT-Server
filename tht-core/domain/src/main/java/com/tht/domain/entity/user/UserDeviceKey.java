package com.tht.domain.entity.user;

import com.tht.domain.entity.Auditable;
import com.tht.thtcommonutils.utils.LogWriteUtils;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@ToString
@DynamicUpdate
@NoArgsConstructor
public class UserDeviceKey extends Auditable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String userUuid;

    @Column
    private String deviceKey;

    @Builder(access = AccessLevel.PRIVATE)
    public UserDeviceKey(String userUuid, String deviceKey) {
        this.userUuid = userUuid;
        this.deviceKey = deviceKey;
    }

    public static UserDeviceKey create(final String userUuid, final String deviceKey) {
        LogWriteUtils.logInfo(
            "UserDeviceKey_create : " + "{userUuid : " + userUuid + ", deviceKey : " + deviceKey
                + "}");

        return UserDeviceKey.builder()
            .userUuid(userUuid)
            .deviceKey(deviceKey)
            .build();
    }

    public void update(final String deviceKey) {
        this.deviceKey = deviceKey;
    }
}
