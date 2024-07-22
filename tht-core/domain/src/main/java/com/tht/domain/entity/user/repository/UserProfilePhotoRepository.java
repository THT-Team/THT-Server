package com.tht.domain.entity.user.repository;

import com.tht.domain.entity.user.UserProfilePhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserProfilePhotoRepository extends JpaRepository<UserProfilePhoto, Long> {

    List<UserProfilePhoto> findAllByUserUuid(final String userUuid);
}
