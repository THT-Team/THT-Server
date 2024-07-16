package com.tht.infra.user.repository;

import com.tht.infra.user.UserDeviceKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDeviceKeyRepository extends JpaRepository<UserDeviceKey, Long> {

    Optional<UserDeviceKey> findByUserUuid(final String userUuid);
}
