package com.tht.domain.entity.dailyfalling.repository;

import com.tht.domain.entity.dailyfalling.DailyFallingActiveInfo;
import com.tht.domain.entity.dailyfalling.repository.querydsl.DailyFallingActiveTimeTableCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyFallingActiveTimeTableRepository extends
    JpaRepository<DailyFallingActiveInfo, Integer>,
    DailyFallingActiveTimeTableCustomRepository {

    DailyFallingActiveInfo findTopByOrderByEndDateTimeDesc();

}
