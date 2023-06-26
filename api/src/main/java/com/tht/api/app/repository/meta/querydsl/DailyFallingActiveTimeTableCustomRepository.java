package com.tht.api.app.repository.meta.querydsl;

import com.tht.api.app.entity.meta.DailyFallingActiveTimeTable;
import java.util.Optional;

public interface DailyFallingActiveTimeTableCustomRepository {

    Optional<DailyFallingActiveTimeTable> findByActiveNow();
}
