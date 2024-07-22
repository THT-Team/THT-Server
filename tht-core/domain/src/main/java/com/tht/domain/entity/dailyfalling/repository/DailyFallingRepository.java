package com.tht.domain.entity.dailyfalling.repository;

import com.tht.domain.entity.dailyfalling.DailyFalling;
import com.tht.domain.entity.dailyfalling.repository.querydsl.DailyFallingCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyFallingRepository extends JpaRepository<DailyFalling, Long>,
    DailyFallingCustomRepository {

}
