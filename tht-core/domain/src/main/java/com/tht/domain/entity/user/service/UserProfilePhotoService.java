package com.tht.domain.entity.user.service;

import java.util.List;

import com.tht.domain.entity.user.UserProfilePhoto;
import com.tht.domain.entity.user.repository.UserProfilePhotoRepository;
import com.tht.thtcommonutils.utils.LogWriteUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserProfilePhotoService {

    private final UserProfilePhotoRepository repository;

    public List<UserProfilePhoto> createOf(final List<UserProfilePhoto> entity) {
        LogWriteUtils.logInfo("new_user_profile_photo_info : " + entity);
        return repository.saveAll(entity);
    }

    public List<UserProfilePhoto> findByUuid(final String userUuid) {
        return repository.findAllByUserUuid(userUuid);
    }

    public List<UserProfilePhoto> updateAll(final String userUuid, final List<UserProfilePhoto> toList) {

        repository.deleteAll(findByUuid(userUuid));
        return createOf(toList);
    }
}
