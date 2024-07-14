package com.tht.infra.dailyfalling.repository.querydsl;


import com.tht.infra.dailyfalling.mapper.DailyFallingMapper;

import java.util.List;

public interface DailyFallingCustomRepository {

    List<DailyFallingMapper> findAllDailyFallingInfo(final Integer activeTableIdx);

}
