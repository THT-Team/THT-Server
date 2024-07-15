package com.tht.infra.dailyfalling.repository;

import com.tht.infra.dailyfalling.DailyFallingActiveInfo;
import com.tht.infra.dailyfalling.repository.querydsl.DailyFallingActiveTimeTableCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyFallingActiveTimeTableRepository extends
    JpaRepository<DailyFallingActiveInfo, Integer>,
    DailyFallingActiveTimeTableCustomRepository {

    DailyFallingActiveInfo findTopByOrderByEndDateTimeDesc();

}
