package com.tht.infra.user.repository;

import com.tht.enums.EntityState;
import com.tht.infra.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>  {

    boolean existsByUsernameAndStateEquals(final String userName, final EntityState state);
    boolean existsByPhoneNumberAndStateEquals(final String phoneNumber, final EntityState state);

    Optional<User> findByPhoneNumber(final String phoneNumber);
    Optional<User> findByUserUuid(final String userUuid);
}
