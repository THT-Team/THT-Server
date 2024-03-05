package com.tht.api.app.service;

import com.tht.api.app.config.utils.LogWriteUtils;
import com.tht.api.app.entity.user.UserLocationInfo;
import com.tht.api.app.repository.user.UserLocationInfoRepository;
import com.tht.api.exception.custom.UserCustomException;
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

    public UserLocationInfo findByUserUuid(final String userUuid) {
        return repository.findByUserUuid(userUuid)
            .orElseThrow(
                () -> UserCustomException.notExistLocationInfo(userUuid)
            );
    }

    public void update(final String userUuid, final String address, final Integer regionCode,
        final float lat, final float lon) {

        UserLocationInfo userLocationInfo = findByUserUuid(userUuid);
        userLocationInfo.updateLocation(address, regionCode, lat, lon);
    }
}
