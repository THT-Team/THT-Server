package com.tht.thtapis.service;

import java.util.Optional;

import com.tht.infra.dailyfalling.DailyFallingActiveInfo;
import com.tht.infra.dailyfalling.repository.DailyFallingActiveTimeTableRepository;
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
