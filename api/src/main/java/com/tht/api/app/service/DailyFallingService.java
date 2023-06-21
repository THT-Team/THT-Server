package com.tht.api.app.service;

import com.tht.api.app.repository.mapper.DailyFallingMapper;
import com.tht.api.app.repository.meta.DailyFallingRepository;
import com.tht.api.exception.custom.EntityStateException;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DailyFallingService {

    private final DailyFallingRepository dailyFallingRepository;

    public List<DailyFallingMapper> findAllDailyFalling() {

        return dailyFallingRepository.findAllDailyFallingInfo();
    }

    public void existByIdxAndActiveToday(final long dailyFallingIdx) {
        if(!dailyFallingRepository.existsByIdxAndActiveDay(dailyFallingIdx, LocalDate.now())){
            throw new EntityStateException("해당 겂에 해당하는 DailyFalling 가(이) 존재하지 않거나 지난 폴링 주제어 입니다.");
        }
    }
}
