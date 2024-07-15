package com.tht.thtapis.service;

import com.tht.infra.agreement.Agreement;
import com.tht.infra.agreement.AgreementRepository;
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
