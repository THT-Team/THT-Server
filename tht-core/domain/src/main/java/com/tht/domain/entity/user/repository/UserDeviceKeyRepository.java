package com.tht.domain.entity.user.repository;

import com.tht.domain.entity.user.UserDeviceKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDeviceKeyRepository extends JpaRepository<UserDeviceKey, Long> {

    boolean existsByUserUuidAndDeviceKey(final String userUuid, final String deviceKey);
}
