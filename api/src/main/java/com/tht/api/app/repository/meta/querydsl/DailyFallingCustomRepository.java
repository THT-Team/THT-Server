package com.tht.api.app.repository.meta.querydsl;

import com.tht.api.app.repository.mapper.DailyFallingMapper;
import java.util.List;

public interface DailyFallingCustomRepository {

    List<DailyFallingMapper> findAllDailyFallingInfo();
}
