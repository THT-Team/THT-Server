package com.tht.api.app.service;

import com.tht.api.app.entity.user.UserDeviceKey;
import com.tht.api.app.repository.UserDeviceKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDeviceKeyService {

    private final UserDeviceKeyRepository userDeviceKeyRepository;

    public UserDeviceKey create(final UserDeviceKey entity) {
        return userDeviceKeyRepository.save(entity);
    }
}
