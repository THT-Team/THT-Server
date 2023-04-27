package com.tht.api.app.entity.user;

import com.tht.api.app.entity.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public final class UserDeviceKey extends Auditable {

    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "user_uuid")
    private String userUuid;

    @Column(name = "device_key")
    private String deviceKey;

    @Builder(access = AccessLevel.PRIVATE)
    public UserDeviceKey(String userUuid, String deviceKey) {
        this.userUuid = userUuid;
        this.deviceKey = deviceKey;
    }

    public static UserDeviceKey create(final String userUuid, final String deviceKey) {
        return UserDeviceKey.builder()
            .userUuid(userUuid)
            .deviceKey(deviceKey)
            .build();
    }
}
