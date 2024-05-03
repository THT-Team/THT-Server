package com.tht.api.app.repository.meta;

import com.tht.api.app.entity.meta.DailyFallingActiveInfo;
import com.tht.api.app.repository.meta.querydsl.DailyFallingActiveTimeTableCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyFallingActiveTimeTableRepository extends
    JpaRepository<DailyFallingActiveInfo, Integer>,
    DailyFallingActiveTimeTableCustomRepository {

    DailyFallingActiveInfo findTopByOrderByEndDateTimeDesc();

}
