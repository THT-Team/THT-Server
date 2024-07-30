package com.tht.domain.entity.block.repository;

import com.tht.domain.entity.block.UserBlock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBlockRepository extends JpaRepository<UserBlock, Long>, UserBlockCustomRepository {

    boolean existsByUserUuidAndBlockUserUuid(final String userUuid, final String blockUserUuid);
}
