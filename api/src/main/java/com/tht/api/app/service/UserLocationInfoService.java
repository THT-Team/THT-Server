package com.tht.api.app.service;

import com.tht.api.app.entity.user.UserLocationInfo;
import com.tht.api.app.repository.UserLocationInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserLocationInfoService {

    private final UserLocationInfoRepository repository;

    public UserLocationInfo create(final UserLocationInfo entity) {
        return repository.save(entity);
    }
}
