package com.tht.domain.entity.user.repository;

import com.tht.domain.entity.dailyfalling.UserDailyFalling;
import com.tht.domain.entity.user.repository.querydsl.UserDailyFallingCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDailyFallingRepository extends JpaRepository<UserDailyFalling, Long>,
    UserDailyFallingCustomRepository {

}
