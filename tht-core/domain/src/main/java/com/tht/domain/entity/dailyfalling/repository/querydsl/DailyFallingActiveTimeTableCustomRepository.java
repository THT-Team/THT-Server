package com.tht.domain.entity.dailyfalling.repository.querydsl;


import com.tht.domain.entity.dailyfalling.DailyFallingActiveInfo;

import java.util.Optional;

public interface DailyFallingActiveTimeTableCustomRepository {

    Optional<DailyFallingActiveInfo> findByActiveNow();
}
