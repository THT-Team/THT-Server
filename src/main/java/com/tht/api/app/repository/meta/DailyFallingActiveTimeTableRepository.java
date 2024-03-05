package com.tht.api.app.repository.meta;

import com.tht.api.app.entity.meta.DailyFallingActiveTimeTable;
import com.tht.api.app.repository.meta.querydsl.DailyFallingActiveTimeTableCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyFallingActiveTimeTableRepository extends
    JpaRepository<DailyFallingActiveTimeTable, Integer>,
    DailyFallingActiveTimeTableCustomRepository {

    DailyFallingActiveTimeTable findTopByOrderByEndDateTimeDesc();

}
