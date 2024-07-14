package com.tht.infra.user.repository;

import com.tht.infra.dailyfalling.UserDailyFalling;
import com.tht.infra.user.repository.querydsl.UserDailyFallingCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDailyFallingRepository extends JpaRepository<UserDailyFalling, Long>,
    UserDailyFallingCustomRepository {

}
