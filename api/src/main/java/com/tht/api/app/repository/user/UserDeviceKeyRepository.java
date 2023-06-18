package com.tht.api.app.repository.user;

import com.tht.api.app.entity.user.UserDeviceKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDeviceKeyRepository extends JpaRepository<UserDeviceKey, Long> {

    boolean existsByUserUuidAndDeviceKey(final String userUuid, final String deviceKey);
}
