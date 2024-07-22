package com.tht.domain.entity.user.service;

import com.tht.domain.entity.user.UserDeviceKey;
import com.tht.domain.entity.user.repository.UserDeviceKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDeviceKeyService {

    private final UserDeviceKeyRepository userDeviceKeyRepository;

    public void create(final String userUuid, final String deviceKey) {

        if (!userDeviceKeyRepository.existsByUserUuidAndDeviceKey(userUuid, deviceKey)) {
            userDeviceKeyRepository.save(UserDeviceKey.create(userUuid, deviceKey));
        }
    }
}
