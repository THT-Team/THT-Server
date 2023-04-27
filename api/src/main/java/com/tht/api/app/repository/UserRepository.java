package com.tht.api.app.repository;

import com.tht.api.app.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(final String userName);

    boolean existsByPhoneNumber(final String phoneNumber);
}
