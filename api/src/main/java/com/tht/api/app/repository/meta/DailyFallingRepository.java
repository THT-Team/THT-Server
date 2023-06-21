package com.tht.api.app.repository.meta;

import com.tht.api.app.entity.meta.DailyFalling;
import com.tht.api.app.repository.meta.querydsl.DailyFallingCustomRepository;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyFallingRepository extends JpaRepository<DailyFalling, Long>,
    DailyFallingCustomRepository {

    boolean existsByIdxAndActiveDay(final long dailyFallingIdx, final LocalDate localDate);
}
