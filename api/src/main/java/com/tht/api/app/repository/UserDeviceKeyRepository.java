package com.tht.api.app.repository;

import com.tht.api.app.entity.user.UserDeviceKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDeviceKeyRepository extends JpaRepository<UserDeviceKey, Long> {

}
