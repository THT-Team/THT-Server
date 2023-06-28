package com.tht.api.app.repository.user;

import com.tht.api.app.entity.user.UserBlock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBlockRepository extends JpaRepository<UserBlock, Long> {

    boolean existsByUserUuidAndBlockUserUuid(final String userUuid, final String blockUserUuid);
}
