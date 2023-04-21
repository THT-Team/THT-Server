package com.tht.api.app.entity.user;

import com.tht.api.app.entity.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public final class UserDeviceKey extends Auditable {

    @Id
    private Long idx;

    @Column(name = "user_uuid")
    private String userUuid;

    @Column(name = "device_key")
    private String deviceKey;

}
