package com.tht.api.app.service;

import com.tht.api.app.config.utils.LogWriteUtils;
import com.tht.api.app.entity.user.UserLocationInfo;
import com.tht.api.app.repository.user.UserLocationInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserLocationInfoService {

    private final UserLocationInfoRepository repository;

    public UserLocationInfo create(final UserLocationInfo entity) {
        LogWriteUtils.logInfo("new_user_location_info : " + entity);
        return repository.save(entity);
    }
}
