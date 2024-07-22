package com.tht.domain.entity.dailyfalling.repository.querydsl;


import com.tht.domain.entity.dailyfalling.mapper.DailyFallingMapper;

import java.util.List;

public interface DailyFallingCustomRepository {

    List<DailyFallingMapper> findAllDailyFallingInfo(final Integer activeTableIdx);

}
