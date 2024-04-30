package com.tht.api.app.service;

import com.tht.api.app.entity.meta.Agreement;
import com.tht.api.app.repository.meta.AgreementRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
