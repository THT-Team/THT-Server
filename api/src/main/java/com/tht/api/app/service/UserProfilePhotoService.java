package com.tht.api.app.service;

import com.tht.api.app.entity.user.UserProfilePhoto;
import com.tht.api.app.repository.UserProfilePhotoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserProfilePhotoService {

    private final UserProfilePhotoRepository repository;

    public List<UserProfilePhoto> createOf(final List<UserProfilePhoto> entity) {
        return repository.saveAll(entity);
    }

}
