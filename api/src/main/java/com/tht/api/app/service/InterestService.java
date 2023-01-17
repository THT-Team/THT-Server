package com.tht.api.app.service;

import com.tht.api.app.facade.interest.response.InterestResponse;
import com.tht.api.app.repository.meta.InterestRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class InterestService {

    private final InterestRepository interestRepository;

    public List<InterestResponse> findAllList() {
        return interestRepository.findAllBy();
    }
}
