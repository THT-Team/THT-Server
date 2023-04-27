package com.tht.api.app.repository;

import com.tht.api.app.entity.user.UserProfilePhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfilePhotoRepository extends JpaRepository<UserProfilePhoto, Long> {

}
