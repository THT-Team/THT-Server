package com.tht.api.app.service;

import com.tht.api.app.config.utils.LogWriteUtils;
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
        LogWriteUtils.logInfo("new_user_device_key : " + entity);

        return userDeviceKeyRepository.save(entity);
    }

    public void create(final String userUuid, final String deviceKey) {

        if (!userDeviceKeyRepository.existsByUserUuidAndDeviceKey(userUuid, deviceKey)) {
            userDeviceKeyRepository.save(UserDeviceKey.create(userUuid, deviceKey));
        }
    }
}
