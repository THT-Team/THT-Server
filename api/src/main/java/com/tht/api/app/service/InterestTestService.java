package com.tht.api.app.service;

import com.tht.api.app.facade.interestTest.response.InterestTestResponse;
import com.tht.api.app.repository.meta.InterestTestRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InterestTestService {
    private final InterestTestRepository interestTestRepository;

    public List<InterestTestResponse> findAllList() {
        return interestTestRepository.findAllBy();
    }
}
