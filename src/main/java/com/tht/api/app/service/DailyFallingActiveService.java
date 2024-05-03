package com.tht.api.app.service;

import com.tht.api.app.entity.meta.DailyFallingActiveInfo;
import com.tht.api.app.repository.meta.DailyFallingActiveTimeTableRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DailyFallingActiveService {

    private final DailyFallingActiveTimeTableRepository repository;

    public Optional<DailyFallingActiveInfo> findActiveInfo() {
        return repository.findByActiveNow();
    }
}
