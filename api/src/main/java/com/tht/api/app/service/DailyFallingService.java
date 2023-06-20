package com.tht.api.app.service;

import com.tht.api.app.repository.meta.DailyFallingRepository;
import com.tht.api.app.repository.mapper.DailyFallingMapper;
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
}
