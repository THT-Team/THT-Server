package com.tht.domain.entity.user.repository;

import com.tht.domain.entity.user.UserBlock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBlockRepository extends JpaRepository<UserBlock, Long> {

    boolean existsByUserUuidAndBlockUserUuid(final String userUuid, final String blockUserUuid);
}
