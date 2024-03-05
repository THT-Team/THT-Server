package com.tht.api.app.repository.user;

import com.tht.api.app.entity.user.UserLocationInfo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLocationInfoRepository extends JpaRepository<UserLocationInfo, Long> {

    Optional<UserLocationInfo> findByUserUuid(final String userUuid);
}
