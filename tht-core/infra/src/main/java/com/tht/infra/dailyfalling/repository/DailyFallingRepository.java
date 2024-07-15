package com.tht.infra.dailyfalling.repository;

import com.tht.infra.dailyfalling.DailyFalling;
import com.tht.infra.dailyfalling.repository.querydsl.DailyFallingCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyFallingRepository extends JpaRepository<DailyFalling, Long>,
    DailyFallingCustomRepository {

}
