package com.tht.domain.entity.user.repository;

import com.tht.domain.entity.dailyfalling.UserDailyFalling;
import com.tht.domain.entity.user.repository.querydsl.UserDailyFallingCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserDailyFallingRepository extends JpaRepository<UserDailyFalling, Long>,
    UserDailyFallingCustomRepository {

    @Transactional
    void deleteAllByUserUuid(String userUuid);
}
