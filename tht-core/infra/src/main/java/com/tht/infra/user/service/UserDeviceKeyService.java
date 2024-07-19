package com.tht.infra.user.service;

import com.tht.infra.user.UserDeviceKey;
import com.tht.infra.user.repository.UserDeviceKeyRepository;
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
