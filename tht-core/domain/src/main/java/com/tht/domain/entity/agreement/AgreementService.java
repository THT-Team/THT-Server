package com.tht.domain.entity.agreement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AgreementService {

    private final AgreementRepository repository;

    public List<Agreement> findAll() {
        return repository.findAll();
    }
}
