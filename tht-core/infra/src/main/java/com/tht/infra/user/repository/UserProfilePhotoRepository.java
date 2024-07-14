package com.tht.infra.user.repository;

import com.tht.infra.user.UserProfilePhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserProfilePhotoRepository extends JpaRepository<UserProfilePhoto, Long> {

    List<UserProfilePhoto> findAllByUserUuid(final String userUuid);
}
