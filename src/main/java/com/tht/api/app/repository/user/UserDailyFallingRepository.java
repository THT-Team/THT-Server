package com.tht.api.app.repository.user;

import com.tht.api.app.entity.user.UserDailyFalling;
import com.tht.api.app.repository.user.querydsl.UserDailyFallingCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDailyFallingRepository extends JpaRepository<UserDailyFalling, Long>,
    UserDailyFallingCustomRepository {

}
