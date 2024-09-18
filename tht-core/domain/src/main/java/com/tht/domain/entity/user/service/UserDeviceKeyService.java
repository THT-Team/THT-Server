package com.tht.domain.entity.user.service;

import com.tht.domain.entity.user.UserDeviceKey;
import com.tht.domain.entity.user.repository.UserDeviceKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDeviceKeyService {

    private final UserDeviceKeyRepository userDeviceKeyRepository;

    public void update(final String userUuid, final String deviceKey) {

        Optional<UserDeviceKey> userDeviceKey = userDeviceKeyRepository.findByUserUuidAndDeviceKey(userUuid, deviceKey);
        if (userDeviceKey.isEmpty()) {
            userDeviceKeyRepository.save(UserDeviceKey.create(userUuid, deviceKey));
            return;
        }

        userDeviceKey.get().update(deviceKey);
    }
}
