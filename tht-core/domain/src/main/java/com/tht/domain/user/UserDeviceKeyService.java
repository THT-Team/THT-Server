package com.tht.domain.user;

import com.tht.infra.user.UserDeviceKey;
import com.tht.infra.user.repository.UserDeviceKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDeviceKeyService {

    private final UserDeviceKeyRepository userDeviceKeyRepository;

    public void recordDeviceKey(final String userUuid, final String deviceKey) {

        final Optional<UserDeviceKey> userDeviceKey = userDeviceKeyRepository.findByUserUuid(userUuid);

        if (userDeviceKey.isPresent()) {
            userDeviceKey.get().changeKey(deviceKey);
            return;
        }
        userDeviceKeyRepository.save(UserDeviceKey.create(userUuid, deviceKey));
    }

}
