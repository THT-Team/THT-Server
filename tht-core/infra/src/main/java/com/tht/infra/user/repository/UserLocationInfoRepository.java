package com.tht.infra.user.repository;

import com.tht.infra.user.UserLocationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLocationInfoRepository extends JpaRepository<UserLocationInfo, Long> {

    Optional<UserLocationInfo> findByUserUuid(final String userUuid);
}
