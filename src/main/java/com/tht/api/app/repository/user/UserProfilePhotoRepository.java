package com.tht.api.app.repository.user;

import com.tht.api.app.entity.user.UserProfilePhoto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfilePhotoRepository extends JpaRepository<UserProfilePhoto, Long> {

    List<UserProfilePhoto> findAllByUserUuid(final String userUuid);
}
