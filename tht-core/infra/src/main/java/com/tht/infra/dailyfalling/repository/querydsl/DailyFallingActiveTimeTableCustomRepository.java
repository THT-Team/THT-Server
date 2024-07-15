package com.tht.infra.dailyfalling.repository.querydsl;


import com.tht.infra.dailyfalling.DailyFallingActiveInfo;

import java.util.Optional;

public interface DailyFallingActiveTimeTableCustomRepository {

    Optional<DailyFallingActiveInfo> findByActiveNow();
}
