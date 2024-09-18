package com.tht.domain.entity.user.repository;

import com.tht.domain.entity.user.UserDeviceKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDeviceKeyRepository extends JpaRepository<UserDeviceKey, Long> {

    Optional<UserDeviceKey> findByUserUuidAndDeviceKey(final String userUuid, final String deviceKey);
}
