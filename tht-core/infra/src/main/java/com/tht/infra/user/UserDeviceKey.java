package com.tht.infra.user;

import com.tht.infra.Auditable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
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

        return UserDeviceKey.builder()
            .userUuid(userUuid)
            .deviceKey(deviceKey)
            .build();
    }

    public void changeKey(final String deviceKey) {
        if(!this.deviceKey.equals(deviceKey)) {
            this.deviceKey = deviceKey;
        }
    }
}
