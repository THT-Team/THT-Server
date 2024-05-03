package com.tht.api.app.repository.meta.querydsl;

import com.tht.api.app.entity.meta.DailyFallingActiveInfo;

import java.util.Optional;

public interface DailyFallingActiveTimeTableCustomRepository {

    Optional<DailyFallingActiveInfo> findByActiveNow();
}
